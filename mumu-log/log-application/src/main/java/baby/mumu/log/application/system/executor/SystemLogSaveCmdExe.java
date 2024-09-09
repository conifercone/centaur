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
package baby.mumu.log.application.system.executor;

import baby.mumu.log.client.dto.SystemLogSaveCmd;
import baby.mumu.log.domain.system.gateway.SystemLogGateway;
import baby.mumu.log.infrastructure.system.convertor.SystemLogConvertor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 系统日志保存指令执行器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.0
 */
@Component
public class SystemLogSaveCmdExe {

  private final SystemLogGateway systemLogGateway;
  private final SystemLogConvertor systemLogConvertor;

  @Autowired
  public SystemLogSaveCmdExe(SystemLogGateway systemLogGateway,
      SystemLogConvertor systemLogConvertor) {
    this.systemLogGateway = systemLogGateway;
    this.systemLogConvertor = systemLogConvertor;
  }

  public void execute(@NotNull SystemLogSaveCmd systemLogSaveCmd) {
    Assert.notNull(systemLogSaveCmd, "SystemLogSaveCmd cannot be null");
    systemLogConvertor.toEntity(systemLogSaveCmd.getSystemLogSaveCo())
        .ifPresent(systemLogGateway::save);
  }
}