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
package com.sky.centaur.file.infrastructure.streamfile.convertor;

import com.sky.centaur.file.client.dto.co.StreamFileDownloadCo;
import com.sky.centaur.file.client.dto.co.StreamFileRemoveCo;
import com.sky.centaur.file.client.dto.co.StreamFileSyncUploadCo;
import com.sky.centaur.file.domain.stream.StreamFile;
import com.sky.centaur.file.infrastructure.streamfile.gatewayimpl.minio.dataobject.StreamFileMinioDo;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * StreamFile mapstruct转换器
 *
 * @author kaiyu.shan
 * @since 1.0.1
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StreamFileMapper {

  StreamFileMapper INSTANCE = Mappers.getMapper(StreamFileMapper.class);

  @Mappings(value = {
      @Mapping(target = "content", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  StreamFile toEntity(StreamFileSyncUploadCo streamFileSyncUploadCo);

  @Mappings(value = {
      @Mapping(target = "content", ignore = true),
      @Mapping(target = "size", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  StreamFile toEntity(StreamFileRemoveCo streamFileRemoveCo);

  @Mappings(value = {
      @Mapping(target = "content", ignore = true),
      @Mapping(target = "size", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  StreamFile toEntity(StreamFileDownloadCo streamFileDownloadCo);

  @Mappings(value = {
      @Mapping(target = "content", ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  StreamFileMinioDo toMinioDo(StreamFile streamFile);
}
