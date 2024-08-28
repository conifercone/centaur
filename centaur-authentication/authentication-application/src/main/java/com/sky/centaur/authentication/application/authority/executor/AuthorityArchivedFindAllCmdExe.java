/*
 * Copyright (c) 2024-2024, kaiyu.shan@outlook.com.
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

package com.sky.centaur.authentication.application.authority.executor;

import com.sky.centaur.authentication.client.dto.AuthorityArchivedFindAllCmd;
import com.sky.centaur.authentication.client.dto.co.AuthorityArchivedFindAllCo;
import com.sky.centaur.authentication.domain.authority.Authority;
import com.sky.centaur.authentication.domain.authority.gateway.AuthorityGateway;
import com.sky.centaur.authentication.infrastructure.authority.convertor.AuthorityConvertor;
import io.micrometer.observation.annotation.Observed;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 查询权限已归档指令执行器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.5
 */
@Component
@Observed(name = "AuthorityArchivedFindAllCmdExe")
public class AuthorityArchivedFindAllCmdExe {

  private final AuthorityGateway authorityGateway;
  private final AuthorityConvertor authorityConvertor;

  @Autowired
  public AuthorityArchivedFindAllCmdExe(AuthorityGateway authorityGateway,
      AuthorityConvertor authorityConvertor) {
    this.authorityGateway = authorityGateway;
    this.authorityConvertor = authorityConvertor;
  }

  public Page<AuthorityArchivedFindAllCo> execute(
      AuthorityArchivedFindAllCmd authorityArchivedFindAllCmd) {
    Assert.notNull(authorityArchivedFindAllCmd, "AuthorityArchivedFindAllCmd cannot be null");
    Authority authority = authorityConvertor.toEntity(
            authorityArchivedFindAllCmd.getAuthorityArchivedFindAllCo())
        .orElseGet(Authority::new);
    Page<Authority> authorities = authorityGateway.findArchivedAll(authority,
        authorityArchivedFindAllCmd.getPageNo(), authorityArchivedFindAllCmd.getPageSize());
    List<AuthorityArchivedFindAllCo> authorityArchivedFindAllCoList = authorities.getContent()
        .stream()
        .map(authorityConvertor::toArchivedFindAllCo)
        .filter(Optional::isPresent).map(Optional::get).toList();
    return new PageImpl<>(authorityArchivedFindAllCoList, authorities.getPageable(),
        authorities.getTotalElements());
  }
}