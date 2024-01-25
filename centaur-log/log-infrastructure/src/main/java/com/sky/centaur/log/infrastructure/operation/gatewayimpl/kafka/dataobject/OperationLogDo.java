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
package com.sky.centaur.log.infrastructure.operation.gatewayimpl.kafka.dataobject;

import lombok.Data;

/**
 * 操作日志数据对象
 *
 * @author 单开宇
 * @since 2024-01-25
 */
@Data
public class OperationLogDo {

  /**
   * 日志内容
   */
  private String content;

  /**
   * 操作日志的执行人
   */
  private String operator;

  /**
   * 操作日志绑定的业务对象标识
   */
  private String bizNo;

  /**
   * 操作日志的种类
   */
  private String category;

  /**
   * 扩展参数，记录操作日志的修改详情
   */
  private String detail;
}
