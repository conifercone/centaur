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

package com.sky.centaur.unique.application.pk.executor;

import com.sky.centaur.unique.domain.pk.gateway.PrimaryKeyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 雪花算法主键生成
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.0
 */
@Component
public class PrimaryKeySnowflakeGenerateExe {

  private final PrimaryKeyGateway primaryKeyGateway;

  @Autowired
  public PrimaryKeySnowflakeGenerateExe(PrimaryKeyGateway primaryKeyGateway) {
    this.primaryKeyGateway = primaryKeyGateway;
  }

  public long execute() {
    return primaryKeyGateway.snowflake();
  }
}
