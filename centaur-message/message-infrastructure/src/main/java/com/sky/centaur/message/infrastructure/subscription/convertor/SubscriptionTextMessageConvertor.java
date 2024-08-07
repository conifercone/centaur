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
package com.sky.centaur.message.infrastructure.subscription.convertor;

import com.sky.centaur.basis.kotlin.tools.SecurityContextUtil;
import com.sky.centaur.extension.translation.SimpleTextTranslation;
import com.sky.centaur.message.client.dto.co.SubscriptionTextMessageFindAllWithSomeOneCo;
import com.sky.centaur.message.client.dto.co.SubscriptionTextMessageFindAllYouSendCo;
import com.sky.centaur.message.client.dto.co.SubscriptionTextMessageForwardCo;
import com.sky.centaur.message.domain.subscription.SubscriptionTextMessage;
import com.sky.centaur.message.infrastructure.subscription.gatewayimpl.database.dataobject.SubscriptionTextMessageDo;
import com.sky.centaur.unique.client.api.PrimaryKeyGrpcService;
import java.util.Optional;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文本订阅消息转换器转换器
 *
 * @author kaiyu.shan
 * @since 1.0.2
 */
@Component
public class SubscriptionTextMessageConvertor {

  private final PrimaryKeyGrpcService primaryKeyGrpcService;
  private final SimpleTextTranslation simpleTextTranslation;

  @Autowired
  public SubscriptionTextMessageConvertor(PrimaryKeyGrpcService primaryKeyGrpcService,
      ObjectProvider<SimpleTextTranslation> simpleTextTranslations) {
    this.primaryKeyGrpcService = primaryKeyGrpcService;
    this.simpleTextTranslation = simpleTextTranslations.getIfAvailable();
  }

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.2")
  public Optional<SubscriptionTextMessage> toEntity(
      SubscriptionTextMessageForwardCo subscriptionTextMessageForwardCo) {
    return Optional.ofNullable(subscriptionTextMessageForwardCo)
        .flatMap(res -> SecurityContextUtil.getLoginAccountId().map(senderAccountId -> {
          SubscriptionTextMessage entity = SubscriptionTextMessageMapper.INSTANCE.toEntity(res);
          entity.setSenderId(senderAccountId);
          Optional.ofNullable(entity.getId()).ifPresentOrElse(id -> {
          }, () -> {
            Long id = primaryKeyGrpcService.snowflake();
            entity.setId(id);
            res.setId(id);
          });
          return entity;
        }));
  }

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.2")
  public Optional<SubscriptionTextMessageDo> toDataObject(
      SubscriptionTextMessage subscriptionTextMessage) {
    return Optional.ofNullable(subscriptionTextMessage)
        .map(SubscriptionTextMessageMapper.INSTANCE::toDataObject);
  }

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.3")
  public Optional<SubscriptionTextMessage> toEntity(
      SubscriptionTextMessageDo subscriptionTextMessageDo) {
    return Optional.ofNullable(subscriptionTextMessageDo)
        .map(SubscriptionTextMessageMapper.INSTANCE::toEntity);
  }

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.3")
  public Optional<SubscriptionTextMessage> toEntity(
      SubscriptionTextMessageFindAllYouSendCo subscriptionTextMessageFindAllYouSendCo) {
    return Optional.ofNullable(subscriptionTextMessageFindAllYouSendCo)
        .map(SubscriptionTextMessageMapper.INSTANCE::toEntity);
  }

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.3")
  public Optional<SubscriptionTextMessageFindAllYouSendCo> toFindAllYouSendCo(
      SubscriptionTextMessage subscriptionTextMessage) {
    return Optional.ofNullable(subscriptionTextMessage)
        .map(SubscriptionTextMessageMapper.INSTANCE::toFindAllYouSendCo)
        .map(subscriptionTextMessageFindAllYouSendCo -> {
          Optional.ofNullable(simpleTextTranslation).flatMap(
                  simpleTextTranslationBean -> simpleTextTranslationBean.translateToAccountLanguageIfPossible(
                      subscriptionTextMessageFindAllYouSendCo.getMessage()))
              .ifPresent(subscriptionTextMessageFindAllYouSendCo::setMessage);
          return subscriptionTextMessageFindAllYouSendCo;
        });
  }

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.3")
  public Optional<SubscriptionTextMessageFindAllWithSomeOneCo> toFindAllWithSomeOne(
      SubscriptionTextMessage subscriptionTextMessage) {
    return Optional.ofNullable(subscriptionTextMessage)
        .map(SubscriptionTextMessageMapper.INSTANCE::toFindAllWithSomeOne)
        .map(subscriptionTextMessageFindAllWithSomeOneCo -> {
          Optional.ofNullable(simpleTextTranslation).flatMap(
                  simpleTextTranslationBean -> simpleTextTranslationBean.translateToAccountLanguageIfPossible(
                      subscriptionTextMessageFindAllWithSomeOneCo.getMessage()))
              .ifPresent(subscriptionTextMessageFindAllWithSomeOneCo::setMessage);
          return subscriptionTextMessageFindAllWithSomeOneCo;
        });
  }
}
