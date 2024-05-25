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
package com.sky.centaur.authentication.client.grpc;

import com.sky.centaur.authentication.client.api.AccountGrpcService;
import com.sky.centaur.authentication.client.api.grpc.AccountRegisterGrpcCmd;
import com.sky.centaur.authentication.client.api.grpc.AccountRegisterGrpcCo;
import com.sky.centaur.authentication.client.api.grpc.SexEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountGrpcService单元测试
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class AccountGrpcServiceTest {

  private final AccountGrpcService accountGrpcService;
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountGrpcServiceTest.class);

  @Autowired
  public AccountGrpcServiceTest(AccountGrpcService accountGrpcService) {
    this.accountGrpcService = accountGrpcService;
  }

  @Test
  @Transactional
  public void register() {
    AccountRegisterGrpcCmd accountRegisterGrpcCmd = AccountRegisterGrpcCmd.newBuilder()
        .setAccountRegisterCo(
            AccountRegisterGrpcCo.newBuilder().setId(926369451).setUsername("test")
                .setPassword("test").setRoleCode("admin").setSex(SexEnum.SEXLESS)
                .build())
        .build();
    AccountRegisterGrpcCo accountRegisterGrpcCo = accountGrpcService.register(
        accountRegisterGrpcCmd);
    LOGGER.info("AccountRegisterGrpcCo: {}", accountRegisterGrpcCo);
    Assertions.assertNotNull(accountRegisterGrpcCo);
    Assertions.assertEquals("test", accountRegisterGrpcCo.getUsername());
  }
}