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

import com.sky.centaur.authentication.client.dto.AuthorityFindAllCmd;
import com.sky.centaur.authentication.client.dto.co.AuthorityFindAllCo;
import com.sky.centaur.authentication.domain.authority.Authority;
import com.sky.centaur.authentication.domain.authority.gateway.AuthorityGateway;
import com.sky.centaur.authentication.infrastructure.authority.convertor.AuthorityConvertor;
import io.micrometer.observation.annotation.Observed;
import jakarta.annotation.Resource;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

/**
 * 查询权限指令执行器
 *
 * @author 单开宇
 * @since 2024-02-23
 */
@Component
@Observed(name = "AuthorityFindAllCmdExe")
public class AuthorityFindAllCmdExe {

  @Resource
  private AuthorityGateway authorityGateway;

  public Page<AuthorityFindAllCo> execute(@NotNull AuthorityFindAllCmd authorityFindAllCmd) {
    Authority authority = AuthorityConvertor.toEntity(authorityFindAllCmd.getAuthorityFindAllCo());
    Page<Authority> authorities = authorityGateway.findAll(authority,
        authorityFindAllCmd.getPageNo(), authorityFindAllCmd.getPageSize());
    List<AuthorityFindAllCo> authorityFindAllCoList = authorities.getContent().stream()
        .map(AuthorityConvertor::toFindAllCo).toList();
    return new PageImpl<>(authorityFindAllCoList, authorities.getPageable(),
        authorities.getTotalElements());
  }
}
