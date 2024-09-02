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
package com.sky.centaur.unique.application.service;

import com.sky.centaur.unique.application.country.executor.CountryGetAllExe;
import com.sky.centaur.unique.application.country.executor.CountryGetCitiesByStateIdCmdExe;
import com.sky.centaur.unique.application.country.executor.CountryGetCityByIdCmdExe;
import com.sky.centaur.unique.application.country.executor.CountryGetStateByIdCmdExe;
import com.sky.centaur.unique.application.country.executor.CountryGetStateCitiesByIdCmdExe;
import com.sky.centaur.unique.application.country.executor.CountryGetStatesByCountryIdCmdExe;
import com.sky.centaur.unique.application.country.executor.CountryStateCityGetAllExe;
import com.sky.centaur.unique.client.api.CountryService;
import com.sky.centaur.unique.client.dto.CountryGetCitiesByStateIdCmd;
import com.sky.centaur.unique.client.dto.CountryGetCityByIdCmd;
import com.sky.centaur.unique.client.dto.CountryGetStateByIdCmd;
import com.sky.centaur.unique.client.dto.CountryGetStateCitiesByIdCmd;
import com.sky.centaur.unique.client.dto.CountryGetStatesByCountryIdCmd;
import com.sky.centaur.unique.client.dto.co.CountryGetAllCo;
import com.sky.centaur.unique.client.dto.co.CountryGetCitiesByStateIdCo;
import com.sky.centaur.unique.client.dto.co.CountryGetCityByIdCo;
import com.sky.centaur.unique.client.dto.co.CountryGetStateByIdCo;
import com.sky.centaur.unique.client.dto.co.CountryGetStateCitiesByIdCo;
import com.sky.centaur.unique.client.dto.co.CountryGetStatesByCountryIdCo;
import com.sky.centaur.unique.client.dto.co.CountryStateCityGetAllCo;
import io.micrometer.core.instrument.binder.grpc.ObservationGrpcServerInterceptor;
import io.micrometer.observation.annotation.Observed;
import java.util.List;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 国家
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.5
 */
@Service
@GRpcService(interceptors = {ObservationGrpcServerInterceptor.class})
@Observed(name = "CountryServiceImpl")
public class CountryServiceImpl implements CountryService {

  private final CountryStateCityGetAllExe countryStateCityGetAllExe;
  private final CountryGetAllExe countryGetAllExe;
  private final CountryGetStatesByCountryIdCmdExe countryGetStatesByCountryIdCmdExe;
  private final CountryGetCitiesByStateIdCmdExe countryGetCitiesByStateIdCmdExe;
  private final CountryGetStateByIdCmdExe countryGetStateByIdCmdExe;
  private final CountryGetStateCitiesByIdCmdExe countryGetStateCitiesByIdCmdExe;
  private final CountryGetCityByIdCmdExe countryGetCityByIdCmdExe;

  @Autowired
  public CountryServiceImpl(CountryStateCityGetAllExe countryStateCityGetAllExe,
      CountryGetAllExe countryGetAllExe,
      CountryGetStatesByCountryIdCmdExe countryGetStatesByCountryIdCmdExe,
      CountryGetCitiesByStateIdCmdExe countryGetCitiesByStateIdCmdExe,
      CountryGetStateByIdCmdExe countryGetStateByIdCmdExe,
      CountryGetStateCitiesByIdCmdExe countryGetStateCitiesByIdCmdExe,
      CountryGetCityByIdCmdExe countryGetCityByIdCmdExe) {
    this.countryStateCityGetAllExe = countryStateCityGetAllExe;
    this.countryGetAllExe = countryGetAllExe;
    this.countryGetStatesByCountryIdCmdExe = countryGetStatesByCountryIdCmdExe;
    this.countryGetCitiesByStateIdCmdExe = countryGetCitiesByStateIdCmdExe;
    this.countryGetStateByIdCmdExe = countryGetStateByIdCmdExe;
    this.countryGetStateCitiesByIdCmdExe = countryGetStateCitiesByIdCmdExe;
    this.countryGetCityByIdCmdExe = countryGetCityByIdCmdExe;
  }

  @Override
  public List<CountryStateCityGetAllCo> getCountryStateCity() {
    return countryStateCityGetAllExe.execute();
  }

  @Override
  public List<CountryGetAllCo> getCountries() {
    return countryGetAllExe.execute();
  }

  @Override
  public List<CountryGetStatesByCountryIdCo> getStatesByCountryId(
      CountryGetStatesByCountryIdCmd countryGetStatesByCountryIdCmd) {
    return countryGetStatesByCountryIdCmdExe.execute(countryGetStatesByCountryIdCmd);
  }

  @Override
  public List<CountryGetCitiesByStateIdCo> getCitiesByStateId(
      CountryGetCitiesByStateIdCmd countryGetCitiesByStateIdCmd) {
    return countryGetCitiesByStateIdCmdExe.execute(countryGetCitiesByStateIdCmd);
  }

  @Override
  public CountryGetStateByIdCo getStateById(CountryGetStateByIdCmd countryGetStateByIdCmd) {
    return countryGetStateByIdCmdExe.execute(countryGetStateByIdCmd);
  }

  @Override
  public CountryGetStateCitiesByIdCo getStateCitiesById(
      CountryGetStateCitiesByIdCmd countryGetStateCitiesByIdCmd) {
    return countryGetStateCitiesByIdCmdExe.execute(countryGetStateCitiesByIdCmd);
  }

  @Override
  public CountryGetCityByIdCo getCityById(CountryGetCityByIdCmd countryGetCityByIdCmd) {
    return countryGetCityByIdCmdExe.execute(countryGetCityByIdCmd);
  }
}
