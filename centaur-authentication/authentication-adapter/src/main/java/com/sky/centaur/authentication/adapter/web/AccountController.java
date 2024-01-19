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
package com.sky.centaur.authentication.adapter.web;

import com.sky.centaur.authentication.client.api.AccountService;
import com.sky.centaur.authentication.client.dto.AccountRegisterCmd;
import com.sky.centaur.authentication.domain.account.Account;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户相关
 *
 * @author 单开宇
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/account")
public class AccountController {

  @Resource
  private AccountService accountService;

  @PostMapping("/register")
  public Account register(@RequestBody AccountRegisterCmd accountRegisterCmd) {
    return accountService.register(accountRegisterCmd);
  }
}