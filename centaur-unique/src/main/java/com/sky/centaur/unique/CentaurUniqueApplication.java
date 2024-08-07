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

package com.sky.centaur.unique;

import com.github.guang19.leaf.spring.autoconfig.LeafAutoConfiguration;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 分布式主键生成服务
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@SpringBootApplication
@Import(LeafAutoConfiguration.class)
@EnableRedisDocumentRepositories(basePackages = "com.sky.centaur.unique.infrastructure.**")
public class CentaurUniqueApplication {

  public static void main(String[] args) {
    SpringApplication.run(CentaurUniqueApplication.class, args);
  }
}
