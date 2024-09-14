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
package baby.mumu.basis.provider;

import java.util.concurrent.TimeUnit;

/**
 * 限流基本信息自动生成提供者接口
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 2.1.0
 */
public interface RateLimitingCustomGenerateProvider {

  /**
   * 生成令牌容量
   *
   * @return 令牌容量
   */
  int generateCapacity();

  /**
   * 生成周期
   *
   * @return 生成周期
   */
  long generatePeriod();

  /**
   * 生成时间单位
   *
   * @return 时间单位
   */
  TimeUnit generateTimeUnit();
}