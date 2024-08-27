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

import com.sky.centaur.basis.kotlin.tools.CommonUtil;
import com.sky.centaur.message.client.dto.co.SubscriptionTextMessageFindAllWithSomeOneCo;
import com.sky.centaur.message.client.dto.co.SubscriptionTextMessageFindAllYouSendCo;
import com.sky.centaur.message.client.dto.co.SubscriptionTextMessageForwardCo;
import com.sky.centaur.message.domain.subscription.SubscriptionTextMessage;
import com.sky.centaur.message.domain.subscription.SubscriptionTextMessage4Desc;
import com.sky.centaur.message.infrastructure.subscription.gatewayimpl.database.dataobject.SubscriptionTextMessageArchivedDo;
import com.sky.centaur.message.infrastructure.subscription.gatewayimpl.database.dataobject.SubscriptionTextMessageDo;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * SubscriptionTextMessage mapstruct转换器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.2
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubscriptionTextMessageMapper {

  SubscriptionTextMessageMapper INSTANCE = Mappers.getMapper(SubscriptionTextMessageMapper.class);

  @API(status = Status.STABLE, since = "1.0.2")
  @Mappings(value = {
      @Mapping(target = SubscriptionTextMessage4Desc.senderId, ignore = true)
  })
  SubscriptionTextMessage toEntity(
      SubscriptionTextMessageForwardCo subscriptionTextMessageForwardCo);

  @API(status = Status.STABLE, since = "1.0.2")
  SubscriptionTextMessageDo toDataObject(SubscriptionTextMessage subscriptionTextMessage);

  @API(status = Status.STABLE, since = "1.0.3")
  SubscriptionTextMessage toEntity(SubscriptionTextMessageDo subscriptionTextMessageDo);

  @API(status = Status.STABLE, since = "1.0.3")
  SubscriptionTextMessage toEntity(
      SubscriptionTextMessageFindAllYouSendCo subscriptionTextMessageFindAllYouSendCo);

  @API(status = Status.STABLE, since = "1.0.3")
  SubscriptionTextMessageFindAllYouSendCo toFindAllYouSendCo(
      SubscriptionTextMessage subscriptionTextMessage);

  @API(status = Status.STABLE, since = "1.0.3")
  SubscriptionTextMessageFindAllWithSomeOneCo toFindAllWithSomeOne(
      SubscriptionTextMessage subscriptionTextMessage);

  @API(status = Status.STABLE, since = "1.0.4")
  SubscriptionTextMessageArchivedDo toArchiveDo(
      SubscriptionTextMessageDo subscriptionTextMessageDo);

  @API(status = Status.STABLE, since = "1.0.4")
  SubscriptionTextMessageDo toDataObject(
      SubscriptionTextMessageArchivedDo subscriptionTextMessageArchivedDo);

  @AfterMapping
  default void convertToAccountTimezone(
      @MappingTarget SubscriptionTextMessageFindAllYouSendCo subscriptionTextMessageFindAllYouSendCo) {
    CommonUtil.convertToAccountZone(subscriptionTextMessageFindAllYouSendCo);
  }

  @AfterMapping
  default void convertToAccountTimezone(
      @MappingTarget SubscriptionTextMessageFindAllWithSomeOneCo subscriptionTextMessageFindAllWithSomeOneCo) {
    CommonUtil.convertToAccountZone(subscriptionTextMessageFindAllWithSomeOneCo);
  }
}
