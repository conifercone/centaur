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

package com.sky.centaur.authentication.application.role.executor;

import com.sky.centaur.authentication.client.dto.RoleDeleteCmd;
import com.sky.centaur.authentication.client.dto.co.RoleDeleteCo;
import com.sky.centaur.authentication.domain.role.Role;
import com.sky.centaur.authentication.domain.role.gateway.RoleGateway;
import com.sky.centaur.authentication.infrastructure.role.convertor.RoleConvertor;
import io.micrometer.observation.annotation.Observed;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * 角色删除指令执行器
 *
 * @author kaiyu.shan
 * @since 2024-02-23
 */
@Component
@Observed(name = "RoleDeleteCmdExe")
public class RoleDeleteCmdExe {

  @Resource
  private RoleGateway roleGateway;

  public RoleDeleteCo execute(@NotNull RoleDeleteCmd roleDeleteCmd) {
    Role role = RoleConvertor.toEntity(roleDeleteCmd.getRoleDeleteCo());
    roleGateway.delete(role);
    return roleDeleteCmd.getRoleDeleteCo();
  }
}
