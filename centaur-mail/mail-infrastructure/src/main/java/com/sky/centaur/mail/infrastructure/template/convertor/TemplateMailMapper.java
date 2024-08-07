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
package com.sky.centaur.mail.infrastructure.template.convertor;

import com.sky.centaur.mail.client.dto.co.TemplateMailSendCo;
import com.sky.centaur.mail.domain.template.TemplateMail;
import com.sky.centaur.mail.infrastructure.template.gatewayimpl.thymeleaf.dataobject.TemplateMailThymeleafDo;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * TemplateMail mapstruct转换器
 *
 * @author kaiyu.shan
 * @since 1.0.1
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TemplateMailMapper {

  TemplateMailMapper INSTANCE = Mappers.getMapper(TemplateMailMapper.class);

  @Mapping(target = "content", ignore = true)
  @API(status = Status.STABLE, since = "1.0.1")
  TemplateMailThymeleafDo toThymeleafDo(TemplateMail templateMail);

  @API(status = Status.STABLE, since = "1.0.1")
  TemplateMail toEntity(TemplateMailSendCo templateMailSendCo);
}
