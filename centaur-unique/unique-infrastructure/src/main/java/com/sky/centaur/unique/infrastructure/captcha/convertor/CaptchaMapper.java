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
package com.sky.centaur.unique.infrastructure.captcha.convertor;

import com.sky.centaur.unique.client.dto.co.SimpleCaptchaGeneratedCo;
import com.sky.centaur.unique.client.dto.co.SimpleCaptchaVerifyCo;
import com.sky.centaur.unique.domain.captcha.Captcha.SimpleCaptcha;
import com.sky.centaur.unique.infrastructure.captcha.gatewayimpl.redis.dataobject.SimpleCaptchaDo;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * Captcha mapstruct转换器
 *
 * @author kaiyu.shan
 * @since 1.0.1
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CaptchaMapper {

  CaptchaMapper INSTANCE = Mappers.getMapper(CaptchaMapper.class);

  @API(status = Status.STABLE, since = "1.0.1")
  SimpleCaptchaDo toDataObject(SimpleCaptcha simpleCaptcha);

  @Mappings(value = {
      @Mapping(target = "source", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  SimpleCaptcha toEntity(SimpleCaptchaGeneratedCo simpleCaptchaGeneratedCo);

  @Mappings(value = {
      @Mapping(target = "length", ignore = true),
      @Mapping(target = "target", ignore = true),
      @Mapping(target = "ttl", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  SimpleCaptcha toEntity(SimpleCaptchaVerifyCo simpleCaptchaVerifyCo);

  @Mappings(value = {
      @Mapping(target = "creationTime", ignore = true),
      @Mapping(target = "founder", ignore = true),
      @Mapping(target = "modificationTime", ignore = true),
      @Mapping(target = "modifier", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  SimpleCaptchaGeneratedCo toSimpleCaptchaGeneratedCo(SimpleCaptcha simpleCaptcha);
}
