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
package com.sky.centaur.message.application.broadcast.executor;

import com.sky.centaur.message.client.dto.BroadcastTextMessageDeleteByIdCmd;
import com.sky.centaur.message.domain.broadcast.gateway.BroadcastTextMessageGateway;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 文本广播消息根据ID删除指令执行器
 *
 * @author kaiyu.shan
 * @since 1.0.3
 */
@Component
public class BroadcastTextMessageDeleteByIdCmdExe {

  private final BroadcastTextMessageGateway broadcastTextMessageGateway;

  @Autowired
  public BroadcastTextMessageDeleteByIdCmdExe(
      BroadcastTextMessageGateway broadcastTextMessageGateway) {
    this.broadcastTextMessageGateway = broadcastTextMessageGateway;
  }

  public void execute(
      @NotNull BroadcastTextMessageDeleteByIdCmd broadcastTextMessageDeleteByIdCmd) {
    Assert.notNull(broadcastTextMessageDeleteByIdCmd,
        "BroadcastTextMessageDeleteByIdCmd cannot null");
    broadcastTextMessageGateway.deleteMsgById(broadcastTextMessageDeleteByIdCmd.getId());
  }
}
