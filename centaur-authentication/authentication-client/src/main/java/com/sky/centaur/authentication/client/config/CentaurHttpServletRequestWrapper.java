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
package com.sky.centaur.authentication.client.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.Locale;

/**
 * HttpServletRequest包装类
 *
 * @author kaiyu.shan
 * @since 1.0.2
 */
public class CentaurHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private Locale customLocale;

  public CentaurHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  public void setLocale(Locale locale) {
    this.customLocale = locale;
  }

  @Override
  public Locale getLocale() {
    if (customLocale != null) {
      return customLocale;
    }
    return super.getLocale();
  }
}
