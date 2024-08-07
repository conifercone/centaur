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
package com.sky.centaur.extension.authentication;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 认证相关配置
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@Data
public class AuthenticationProperties {

  /**
   * 初始密码
   */
  private String initialPassword = "3c380190e5c34f3bc97cae24ac6062";

  @NestedConfigurationProperty
  private Rsa rsa = new Rsa();


  @Data
  public static class Rsa {

    /**
     * 自动生成
     */
    private boolean automaticGenerated = true;

    /**
     * 密钥地址
     */
    private String jksKeyPath;
    /**
     * 密钥密码
     */
    private String jksKeyPassword;

    /**
     * 密钥对
     */
    private String jksKeyPair = "jwt";
  }
}
