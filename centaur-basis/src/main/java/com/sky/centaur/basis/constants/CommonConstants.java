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
package com.sky.centaur.basis.constants;

/**
 * 常用常量类
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
public final class CommonConstants {

  /**
   * 百分号
   */
  public static final String PERCENT_SIGN = "%";

  /**
   * string格式化占位符
   */
  public static final String STRING_FORMAT = "%s";

  /**
   * sql 左右模糊查询模板
   */
  public static final String LEFT_AND_RIGHT_FUZZY_QUERY_TEMPLATE = PERCENT_SIGN.concat(PERCENT_SIGN)
      .concat(
          STRING_FORMAT).concat(PERCENT_SIGN).concat(PERCENT_SIGN);
}