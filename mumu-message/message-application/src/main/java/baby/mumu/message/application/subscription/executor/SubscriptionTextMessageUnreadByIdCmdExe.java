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
package baby.mumu.message.application.subscription.executor;

import baby.mumu.message.client.dto.SubscriptionTextMessageUnreadByIdCmd;
import baby.mumu.message.domain.subscription.gateway.SubscriptionTextMessageGateway;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 文本订阅消息根据ID未读指令执行器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.3
 */
@Component
public class SubscriptionTextMessageUnreadByIdCmdExe {

  private final SubscriptionTextMessageGateway subscriptionTextMessageGateway;

  @Autowired
  public SubscriptionTextMessageUnreadByIdCmdExe(
      SubscriptionTextMessageGateway subscriptionTextMessageGateway) {
    this.subscriptionTextMessageGateway = subscriptionTextMessageGateway;
  }

  public void execute(
      @NotNull SubscriptionTextMessageUnreadByIdCmd subscriptionTextMessageUnreadByIdCmd) {
    Assert.notNull(subscriptionTextMessageUnreadByIdCmd,
        "SubscriptionTextMessageUnreadByIdCmd cannot null");
    subscriptionTextMessageGateway.unreadMsgById(subscriptionTextMessageUnreadByIdCmd.getId());
  }
}