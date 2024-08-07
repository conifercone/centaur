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
package com.sky.centaur.authentication.application.account.executor;

import com.sky.centaur.authentication.application.CaptchaVerify;
import com.sky.centaur.authentication.client.dto.AccountRegisterCmd;
import com.sky.centaur.authentication.domain.account.gateway.AccountGateway;
import com.sky.centaur.authentication.infrastructure.account.convertor.AccountConvertor;
import com.sky.centaur.unique.client.api.CaptchaGrpcService;
import io.micrometer.observation.annotation.Observed;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 账户注册指令执行器
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@Component
@Observed(name = "AccountRegisterCmdExe")
public class AccountRegisterCmdExe extends CaptchaVerify {

  private final AccountGateway accountGateway;
  private final AccountConvertor accountConvertor;

  @Autowired
  public AccountRegisterCmdExe(AccountGateway accountGateway,
      CaptchaGrpcService captchaGrpcService, AccountConvertor accountConvertor) {
    super(captchaGrpcService);
    this.accountGateway = accountGateway;
    this.accountConvertor = accountConvertor;
  }

  public void execute(@NotNull AccountRegisterCmd accountRegisterCmd) {
    verifyCaptcha(accountRegisterCmd.getCaptchaId(), accountRegisterCmd.getCaptcha());
    accountConvertor.toEntity(accountRegisterCmd.getAccountRegisterCo())
        .ifPresent(accountGateway::register);
  }
}
