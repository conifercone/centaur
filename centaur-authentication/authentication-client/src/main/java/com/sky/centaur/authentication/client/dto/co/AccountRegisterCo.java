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
package com.sky.centaur.authentication.client.dto.co;

import com.sky.centaur.basis.client.dto.co.BaseClientObject;
import com.sky.centaur.basis.enums.LanguageEnum;
import com.sky.centaur.basis.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户信息注册客户端对象
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountRegisterCo extends BaseClientObject {

  private Long id;

  @NotBlank(message = "{account.username.validation.not.blank}")
  private String username;

  @NotBlank(message = "{account.password.validation.not.blank}")
  private String password;

  private String roleCode;

  private String avatarUrl;

  private String phone;

  private SexEnum sex;

  @NotBlank(message = "{account.email.validation.not.blank}")
  private String email;

  private String timezone;

  private LanguageEnum language;

  @NotNull(message = "{account.birthday.validation.not.null}")
  private LocalDate birthday;
}
