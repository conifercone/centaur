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
package com.sky.centaur.log.infrastructure.operation.convertor;

import com.sky.centaur.basis.tools.SpringContextUtil;
import com.sky.centaur.log.client.dto.co.OperationLogSaveCo;
import com.sky.centaur.log.client.dto.co.OperationLogSubmitCo;
import com.sky.centaur.log.domain.operation.OperationLog;
import com.sky.centaur.log.infrastructure.operation.gatewayimpl.elasticsearch.dataobject.OperationLogEsDo;
import com.sky.centaur.log.infrastructure.operation.gatewayimpl.kafka.dataobject.OperationLogKafkaDo;
import com.sky.centaur.log.infrastructure.operation.gatewayimpl.redis.dataobject.OperationLogRedisDo;
import com.sky.centaur.unique.client.api.PrimaryKeyGrpcService;
import io.micrometer.tracing.Tracer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

/**
 * 操作日志转换器
 *
 * @author 单开宇
 * @since 2024-01-16
 */
public class OperationLogConvertor {


  @Contract("_ -> new")
  public static @NotNull OperationLogKafkaDo toKafkaDataObject(@NotNull OperationLog operationLog) {
    OperationLogKafkaDo operationLogKafkaDo = new OperationLogKafkaDo();
    BeanUtils.copyProperties(operationLog, operationLogKafkaDo);
    return operationLogKafkaDo;
  }

  @Contract("_ -> new")
  public static @NotNull OperationLogEsDo toEsDataObject(@NotNull OperationLog operationLog) {
    OperationLogEsDo operationLogEsDo = new OperationLogEsDo();
    BeanUtils.copyProperties(operationLog, operationLogEsDo);
    return operationLogEsDo;
  }

  @Contract("_ -> new")
  public static @NotNull OperationLogRedisDo toRedisDataObject(@NotNull OperationLog operationLog) {
    OperationLogRedisDo operationLogRedisDo = new OperationLogRedisDo();
    BeanUtils.copyProperties(operationLog, operationLogRedisDo);
    return operationLogRedisDo;
  }

  @Contract("_ -> new")
  public static @NotNull OperationLog toEntity(@NotNull OperationLogSubmitCo operationLogSubmitCo) {
    OperationLog operationLog = new OperationLog();
    BeanUtils.copyProperties(operationLogSubmitCo, operationLog);
    operationLog.setId(
        Optional.ofNullable(SpringContextUtil.getBean(Tracer.class).currentSpan())
            .map(span -> span.context().traceId()).orElseGet(() ->
                String.valueOf(SpringContextUtil.getBean(PrimaryKeyGrpcService.class).snowflake()))
    );
    operationLog.setOperatingTime(LocalDateTime.now(ZoneId.of("UTC")));
    return operationLog;
  }

  @Contract("_ -> new")
  public static @NotNull OperationLog toEntity(@NotNull OperationLogSaveCo operationLogSaveCo) {
    OperationLog operationLog = new OperationLog();
    BeanUtils.copyProperties(operationLogSaveCo, operationLog);
    return operationLog;
  }

  @Contract("_ -> new")
  public static @NotNull OperationLog toEntity(@NotNull OperationLogRedisDo operationLogRedisDo) {
    OperationLog operationLog = new OperationLog();
    BeanUtils.copyProperties(operationLogRedisDo, operationLog);
    return operationLog;
  }

  @Contract("_ -> new")
  public static @NotNull OperationLog toEntity(@NotNull OperationLogEsDo operationLogEsDo) {
    OperationLog operationLog = new OperationLog();
    BeanUtils.copyProperties(operationLogEsDo, operationLog);
    return operationLog;
  }

}
