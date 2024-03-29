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
package com.sky.centaur.authentication.infrastructure.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * 鉴权服务全局配置信息
 *
 * @author 单开宇
 * @since 2024-01-19
 */
@Data
@Component
@ConfigurationProperties("centaur.auth")
public class AuthenticationProperties {

  @NestedConfigurationProperty
  private Security security = new Security();

  /**
   * 是否开启服务日志
   */
  private boolean enableLog = false;

  @Data
  public static class Security {

    private List<String> excludeUrls = new ArrayList<>();
  }
}
