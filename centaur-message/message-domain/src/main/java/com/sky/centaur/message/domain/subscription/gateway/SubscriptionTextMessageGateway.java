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
package com.sky.centaur.message.domain.subscription.gateway;

import com.sky.centaur.message.domain.subscription.SubscriptionTextMessage;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

/**
 * 文本订阅消息领域网关
 *
 * @author kaiyu.shan
 * @since 1.0.2
 */
public interface SubscriptionTextMessageGateway {

  /**
   * 消息转发
   *
   * @param msg 文本订阅消息
   * @since 1.0.2
   */
  @API(status = Status.STABLE, since = "1.0.2")
  void forwardMsg(SubscriptionTextMessage msg);

  /**
   * 根据ID已读消息
   *
   * @param id 消息ID
   * @since 1.0.3
   */
  @API(status = Status.STABLE, since = "1.0.3")
  void readMsgById(Long id);

  /**
   * 根据ID删除消息
   *
   * @param id 消息ID
   * @since 1.0.3
   */
  @API(status = Status.STABLE, since = "1.0.3")
  void deleteMsgById(Long id);
}
