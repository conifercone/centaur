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
package baby.mumu.authentication.application.role.executor;

import baby.mumu.authentication.client.dto.RoleArchivedFindAllCmd;
import baby.mumu.authentication.client.dto.co.RoleArchivedFindAllCo;
import baby.mumu.authentication.domain.role.Role;
import baby.mumu.authentication.domain.role.gateway.RoleGateway;
import baby.mumu.authentication.infrastructure.role.convertor.RoleConvertor;
import io.micrometer.observation.annotation.Observed;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

/**
 * 已归档角色查询指令执行器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 2.2.0
 */
@Component
@Observed(name = "RoleArchivedFindAllCmdExe")
public class RoleArchivedFindAllCmdExe {

  private final RoleGateway roleGateway;
  private final RoleConvertor roleConvertor;

  @Autowired
  public RoleArchivedFindAllCmdExe(RoleGateway roleGateway, RoleConvertor roleConvertor) {
    this.roleGateway = roleGateway;
    this.roleConvertor = roleConvertor;
  }

  public Page<RoleArchivedFindAllCo> execute(
      @NotNull RoleArchivedFindAllCmd roleArchivedFindAllCmd) {
    Role role = roleConvertor.toEntity(roleArchivedFindAllCmd.getRoleArchivedFindAllQueryCo())
        .orElseGet(Role::new);
    Page<Role> roles = roleGateway.findArchivedAll(role,
        roleArchivedFindAllCmd.getPageNo(), roleArchivedFindAllCmd.getPageSize());
    List<RoleArchivedFindAllCo> roleArchivedFindAllCos = roles.getContent().stream()
        .map(roleConvertor::toArchivedFindAllCo)
        .filter(Optional::isPresent).map(Optional::get).toList();
    return new PageImpl<>(roleArchivedFindAllCos, roles.getPageable(),
        roles.getTotalElements());
  }
}