/*
 * Copyright (c) 2024-2024, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package baby.mumu.authentication.infrastructure.role.gatewayimpl;

import static baby.mumu.basis.constants.CommonConstants.LEFT_AND_RIGHT_FUZZY_QUERY_TEMPLATE;

import baby.mumu.authentication.domain.account.Account;
import baby.mumu.authentication.domain.account.gateway.AccountGateway;
import baby.mumu.authentication.domain.role.Role;
import baby.mumu.authentication.domain.role.gateway.RoleGateway;
import baby.mumu.authentication.infrastructure.relations.database.RoleAuthorityDo;
import baby.mumu.authentication.infrastructure.relations.database.RoleAuthorityRepository;
import baby.mumu.authentication.infrastructure.role.convertor.RoleConvertor;
import baby.mumu.authentication.infrastructure.role.gatewayimpl.database.RoleArchivedRepository;
import baby.mumu.authentication.infrastructure.role.gatewayimpl.database.RoleRepository;
import baby.mumu.authentication.infrastructure.role.gatewayimpl.database.dataobject.RoleDo;
import baby.mumu.authentication.infrastructure.role.gatewayimpl.database.dataobject.RoleDo_;
import baby.mumu.basis.annotations.DangerousOperation;
import baby.mumu.basis.exception.MuMuException;
import baby.mumu.basis.response.ResultCode;
import baby.mumu.extension.ExtensionProperties;
import baby.mumu.extension.GlobalProperties;
import baby.mumu.extension.distributed.lock.DistributedLock;
import io.micrometer.observation.annotation.Observed;
import jakarta.persistence.criteria.Predicate;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色领域网关实现
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.0
 */
@Component
@Observed(name = "RoleGatewayImpl")
public class RoleGatewayImpl implements RoleGateway {

  private final RoleRepository roleRepository;
  private final DistributedLock distributedLock;
  private final AccountGateway accountGateway;
  private final RoleConvertor roleConvertor;
  private final RoleArchivedRepository roleArchivedRepository;
  private final JobScheduler jobScheduler;
  private final ExtensionProperties extensionProperties;
  private final RoleAuthorityRepository roleAuthorityRepository;

  public RoleGatewayImpl(RoleRepository roleRepository,
      ObjectProvider<DistributedLock> distributedLockObjectProvider,
      AccountGateway accountGateway, RoleConvertor roleConvertor,
      RoleArchivedRepository roleArchivedRepository, JobScheduler jobScheduler,
      ExtensionProperties extensionProperties,
      RoleAuthorityRepository roleAuthorityRepository) {
    this.roleRepository = roleRepository;
    this.accountGateway = accountGateway;
    this.distributedLock = distributedLockObjectProvider.getIfAvailable();
    this.roleConvertor = roleConvertor;
    this.roleArchivedRepository = roleArchivedRepository;
    this.jobScheduler = jobScheduler;
    this.extensionProperties = extensionProperties;
    this.roleAuthorityRepository = roleAuthorityRepository;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @API(status = Status.STABLE, since = "1.0.0")
  public void add(Role role) {
    //保存角色数据
    Optional.ofNullable(role).flatMap(roleConvertor::toDataObject)
        .filter(roleDo -> !roleRepository.existsByIdOrCode(roleDo.getId(), roleDo.getCode())
            && !roleArchivedRepository.existsByIdOrCode(roleDo.getId(), roleDo.getCode()))
        .ifPresentOrElse(roleRepository::persist, () -> {
          throw new MuMuException(ResultCode.ROLE_CODE_OR_ID_ALREADY_EXISTS);
        });
    saveRoleAuthorityRelationsData(role);
  }

  @Transactional(rollbackFor = Exception.class)
  @API(status = Status.STABLE, since = "2.1.0")
  protected void saveRoleAuthorityRelationsData(Role role) {
    //保存角色权限关系数据（如果存在关系）
    Optional.ofNullable(role).ifPresent(roleNonNull -> {
      List<RoleAuthorityDo> roleAuthorityDos = roleConvertor.toRoleAuthorityDos(role);
      if (CollectionUtils.isNotEmpty(roleAuthorityDos)) {
        roleAuthorityRepository.persistAll(roleAuthorityDos);
      }
    });
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @API(status = Status.STABLE, since = "1.0.0")
  @DangerousOperation("删除ID为%0的角色")
  public void deleteById(Long id) {
    Optional.ofNullable(id).ifPresent(roleId -> {
      List<Account> allAccountByRoleId = accountGateway.findAllAccountByRoleId(roleId);
      if (CollectionUtils.isNotEmpty(allAccountByRoleId)) {
        throw new MuMuException(ResultCode.ROLE_IS_IN_USE_AND_CANNOT_BE_REMOVED,
            allAccountByRoleId.stream().map(Account::getUsername).toList());
      }
      roleAuthorityRepository.deleteByRoleId(roleId);
      roleRepository.deleteById(roleId);
      roleArchivedRepository.deleteById(roleId);
    });
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @API(status = Status.STABLE, since = "1.0.0")
  public void updateById(Role role) {
    Optional.ofNullable(role).ifPresent(roleDomain -> {
      Optional.ofNullable(distributedLock).ifPresent(DistributedLock::lock);
      try {
        roleConvertor.toDataObject(roleDomain).ifPresent(roleRepository::merge);
        //删除权限关系数据重新添加
        roleAuthorityRepository.deleteByRoleId(roleDomain.getId());
        saveRoleAuthorityRelationsData(roleDomain);
      } finally {
        Optional.ofNullable(distributedLock).ifPresent(DistributedLock::unlock);
      }
    });
  }

  @Override
  @API(status = Status.STABLE, since = "1.0.0")
  @Transactional(rollbackFor = Exception.class)
  public Page<Role> findAll(Role role, int pageNo, int pageSize) {
    Specification<RoleDo> roleDoSpecification = (root, query, cb) -> {
      List<Predicate> predicateList = new ArrayList<>();
      Optional.ofNullable(role.getCode()).filter(StringUtils::isNotBlank)
          .ifPresent(
              code -> predicateList.add(cb.like(root.get(RoleDo_.code),
                  String.format(LEFT_AND_RIGHT_FUZZY_QUERY_TEMPLATE, code))));
      Optional.ofNullable(role.getName()).filter(StringUtils::isNotBlank)
          .ifPresent(
              name -> predicateList.add(cb.like(root.get(RoleDo_.name),
                  String.format(LEFT_AND_RIGHT_FUZZY_QUERY_TEMPLATE, name))));
      Optional.ofNullable(role.getId())
          .ifPresent(id -> predicateList.add(
              cb.equal(root.get(RoleDo_.id), id)));
      assert query != null;
      return query.orderBy(cb.desc(root.get(RoleDo_.creationTime)))
          .where(predicateList.toArray(new Predicate[0]))
          .getRestriction();
    };
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
    Page<RoleDo> roleDoPage = roleRepository.findAll(
        roleDoSpecification, pageRequest);
    return new PageImpl<>(roleDoPage.getContent().stream()
        .flatMap(roleDo -> roleConvertor.toEntity(roleDo).stream())
        .toList(), pageRequest, roleDoPage.getTotalElements());
  }

  @Override
  @API(status = Status.STABLE, since = "1.0.0")
  @Transactional(rollbackFor = Exception.class)
  public List<Role> findAllContainAuthority(Long authorityId) {
    return roleAuthorityRepository.findByAuthorityId(authorityId).stream()
        .flatMap(roleAuthorityDo -> roleConvertor.toEntity(roleAuthorityDo.getRole()).stream())
        .toList();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @DangerousOperation("根据ID归档ID为%0的角色")
  public void archiveById(Long id) {
    List<Account> allAccountByRoleId = accountGateway.findAllAccountByRoleId(id);
    if (CollectionUtils.isNotEmpty(allAccountByRoleId)) {
      throw new MuMuException(ResultCode.ROLE_IS_IN_USE_AND_CANNOT_BE_ARCHIVE,
          allAccountByRoleId.stream().map(Account::getUsername).toList());
    }
    //noinspection DuplicatedCode
    Optional.ofNullable(id).flatMap(roleRepository::findById)
        .flatMap(roleConvertor::toArchivedDo).ifPresent(roleArchivedDo -> {
          roleArchivedDo.setArchived(true);
          roleArchivedRepository.persist(roleArchivedDo);
          roleRepository.deleteById(roleArchivedDo.getId());
          GlobalProperties global = extensionProperties.getGlobal();
          jobScheduler.schedule(Instant.now()
                  .plus(global.getArchiveDeletionPeriod(), global.getArchiveDeletionPeriodUnit()),
              () -> deleteArchivedDataJob(roleArchivedDo.getId()));
        });
  }

  @Job(name = "删除ID为：%0 的角色归档数据")
  @DangerousOperation("根据ID删除ID为%0的角色归档数据定时任务")
  public void deleteArchivedDataJob(Long id) {
    Optional.ofNullable(id)
        .filter(roleId -> accountGateway.findAllAccountByRoleId(roleId).isEmpty())
        .ifPresent(roleIdNotNull -> {
          roleArchivedRepository.deleteById(roleIdNotNull);
          roleAuthorityRepository.deleteByRoleId(roleIdNotNull);
        });
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void recoverFromArchiveById(Long id) {
    Optional.ofNullable(id).flatMap(roleArchivedRepository::findById)
        .flatMap(roleConvertor::toDataObject).ifPresent(roleDo -> {
          roleDo.setArchived(false);
          roleArchivedRepository.deleteById(roleDo.getId());
          roleRepository.persist(roleDo);
        });
  }
}
