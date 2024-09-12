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

import baby.mumu.basis.exception.MuMuException;
import baby.mumu.basis.kotlin.tools.IpUtil;
import baby.mumu.basis.response.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * ip实现
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 2.1.0
 */
public class RateLimitingIpKeyProviderImpl implements RateLimitingKeyProvider {

  private final HttpServletRequest httpServletRequest;

  public RateLimitingIpKeyProviderImpl(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  @Override
  public String generateUniqKey() {
    String ipAddr = IpUtil.getIpAddr(httpServletRequest);
    if (StringUtils.isBlank(ipAddr)) {
      throw new MuMuException(ResultCode.UNABLE_TO_OBTAIN_CURRENT_REQUESTED_IP);
    }
    return ipAddr;
  }
}