/*
 * Copyright (c) 2024-2024, the original author or authors.
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
package baby.mumu.log.infrastructure.operation.convertor;

import baby.mumu.log.client.dto.co.OperationLogFindAllCo;
import baby.mumu.log.client.dto.co.OperationLogFindAllCo4Desc;
import baby.mumu.log.client.dto.co.OperationLogQryCo;
import baby.mumu.log.client.dto.co.OperationLogQryCo4Desc;
import baby.mumu.log.client.dto.co.OperationLogSaveCo;
import baby.mumu.log.client.dto.co.OperationLogSaveCo4Desc;
import baby.mumu.log.client.dto.co.OperationLogSubmitCo;
import baby.mumu.log.domain.operation.OperationLog;
import baby.mumu.log.domain.operation.OperationLog4Desc;
import baby.mumu.log.infrastructure.operation.gatewayimpl.elasticsearch.dataobject.OperationLogEsDo;
import baby.mumu.log.infrastructure.operation.gatewayimpl.kafka.dataobject.OperationLogKafkaDo;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * OperationLog mapstruct转换器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.1
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OperationLogMapper {

  OperationLogMapper INSTANCE = Mappers.getMapper(OperationLogMapper.class);

  OperationLogKafkaDo toKafkaDataObject(OperationLog operationLog);

  OperationLogEsDo toEsDataObject(OperationLog operationLog);

  @Mappings(value = {
      @Mapping(target = OperationLog4Desc.id, ignore = true),
      @Mapping(target = OperationLog4Desc.operatingEndTime, ignore = true),
      @Mapping(target = OperationLog4Desc.operatingStartTime, ignore = true),
      @Mapping(target = OperationLog4Desc.operatingTime, ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  OperationLog toEntity(OperationLogSubmitCo operationLogSubmitCo);

  @Mappings(value = {
      @Mapping(target = OperationLog4Desc.operatingEndTime, ignore = true),
      @Mapping(target = OperationLog4Desc.operatingStartTime, ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  OperationLog toEntity(OperationLogSaveCo operationLogSaveCo);

  @Mappings(value = {
      @Mapping(target = OperationLog4Desc.operatingEndTime, ignore = true),
      @Mapping(target = OperationLog4Desc.operatingStartTime, ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  OperationLog toEntity(OperationLogEsDo operationLogEsDo);

  @Mappings(value = {
      @Mapping(target = OperationLog4Desc.operatingEndTime, ignore = true),
      @Mapping(target = OperationLog4Desc.operatingStartTime, ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  OperationLog toEntity(OperationLogFindAllCo operationLogFindAllCo);

  @API(status = Status.STABLE, since = "1.0.1")
  @Mappings(value = {

      @Mapping(target = OperationLogFindAllCo4Desc.creationTime, ignore = true),
      @Mapping(target = OperationLogFindAllCo4Desc.founder, ignore = true),
      @Mapping(target = OperationLogFindAllCo4Desc.modificationTime, ignore = true),
      @Mapping(target = OperationLogFindAllCo4Desc.modifier, ignore = true),
      @Mapping(target = OperationLogFindAllCo4Desc.archived, ignore = true)
  })
  OperationLogFindAllCo toFindAllCo(OperationLog operationLog);

  @Mappings(value = {
      @Mapping(target = OperationLogQryCo4Desc.creationTime, ignore = true),
      @Mapping(target = OperationLogQryCo4Desc.founder, ignore = true),
      @Mapping(target = OperationLogQryCo4Desc.modificationTime, ignore = true),
      @Mapping(target = OperationLogQryCo4Desc.modifier, ignore = true),
      @Mapping(target = OperationLogQryCo4Desc.archived, ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  OperationLogQryCo toQryCo(OperationLog operationLog);

  @Mappings(value = {
      @Mapping(target = OperationLogSaveCo4Desc.creationTime, ignore = true),
      @Mapping(target = OperationLogSaveCo4Desc.founder, ignore = true),
      @Mapping(target = OperationLogSaveCo4Desc.modificationTime, ignore = true),
      @Mapping(target = OperationLogSaveCo4Desc.modifier, ignore = true),
      @Mapping(target = OperationLogSaveCo4Desc.archived, ignore = true)
  })
  @API(status = Status.STABLE, since = "1.0.1")
  OperationLogSaveCo toSaveCo(OperationLogKafkaDo operationLogKafkaDo);
}
