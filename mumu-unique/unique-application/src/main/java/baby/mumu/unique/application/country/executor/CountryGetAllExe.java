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

package baby.mumu.unique.application.country.executor;

import baby.mumu.unique.client.dto.co.CountryGetAllCo;
import baby.mumu.unique.domain.country.gateway.CountryGateway;
import baby.mumu.unique.infrastructure.country.convertor.CountryConvertor;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取所有国家(不包含省或州、市信息)详细信息
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 2.0.0
 */
@Component
public class CountryGetAllExe {

  private final CountryGateway countryGateway;
  private final CountryConvertor countryConvertor;

  @Autowired
  public CountryGetAllExe(CountryGateway countryGateway,
      CountryConvertor countryConvertor) {
    this.countryGateway = countryGateway;
    this.countryConvertor = countryConvertor;
  }

  public List<CountryGetAllCo> execute() {
    return countryGateway.getCountries().stream()
        .map(country -> countryConvertor.toCountryGetAllCo(country).orElse(null)).filter(
            Objects::nonNull).toList();
  }
}
