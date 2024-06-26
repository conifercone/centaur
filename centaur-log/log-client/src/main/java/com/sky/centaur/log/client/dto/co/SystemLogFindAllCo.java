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
package com.sky.centaur.log.client.dto.co;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.centaur.basis.client.dto.co.BaseClientObject;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 系统日志查询所有客户端对象
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemLogFindAllCo extends BaseClientObject {

  /**
   * 唯一标识
   */
  private String id;

  /**
   * 日志内容
   */
  private String content;

  /**
   * 系统日志的种类
   */
  private String category;

  /**
   * 系统日志成功的文本模板
   */
  private String success;

  /**
   * 系统日志失败的文本模板
   */
  private String fail;

  /**
   * 系统日志的记录时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime recordTime;

}
