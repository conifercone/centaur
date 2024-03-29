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
package com.sky.centaur.authentication.configuration;

import com.google.common.base.Strings;
import com.sky.centaur.authentication.filter.datasource.DataSourceFilter;
import com.sky.centaur.authentication.filter.datasource.DatasourceFilterChain;
import com.sky.centaur.authentication.filter.datasource.DatasourceFilterChainImpl;
import com.sky.centaur.authentication.filter.datasource.P6spyDataSourceFilter;
import com.sky.centaur.authentication.infrastructure.config.AuthenticationProperties;
import com.sky.centaur.basis.dataobject.jpa.CentaurJpaAuditorAware;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 数据源配置类
 *
 * @author 单开宇
 * @since 2024-01-22
 */
@Configuration
public class DatasourceConfiguration {

  /**
   * 创建P6spyWrapperDataSourceFilter的新实例并返回
   *
   * @return 代表P6spyWrapperDataSourceFilter的DataSourceFilter
   */
  @Bean
  @Order(1000)
  public DataSourceFilter p6spyWrapperDataSourceFilter() {
    return new P6spyDataSourceFilter();
  }

  @Bean
  public DataSource datasource(DataSourceProperties dataSourceProperties,
      DatasourceFilterChain dataSourceFilterChain,
      AuthenticationProperties authenticationProperties) {
    HikariConfig config = new HikariConfig();
    config.setUsername(dataSourceProperties.getUsername());
    config.setPassword(dataSourceProperties.getPassword());
    config.setJdbcUrl(dataSourceProperties.getUrl());
    String driverClassName = dataSourceProperties.getDriverClassName();
    if (!Strings.isNullOrEmpty(driverClassName)) {
      config.setDriverClassName(driverClassName);
    }
    HikariDataSource hikariDataSource = new HikariDataSource(config);
    return dataSourceFilterChain.doAfterFilter(hikariDataSource, authenticationProperties);
  }

  /**
   * 创建包含一组DataSourceFilter的DatasourceFilterChain的新实例并返回
   *
   * @param dataSourceFilters 用于构建DatasourceFilterChain的DataSourceFilter列表
   * @return 代表包含指定DataSourceFilter的FilterChain的实例
   */
  @Bean
  public DatasourceFilterChain dataSourceFilterChain(List<DataSourceFilter> dataSourceFilters) {
    return new DatasourceFilterChainImpl(dataSourceFilters);
  }

  @Bean
  public CentaurJpaAuditorAware centaurJpaAuditorAware() {
    return new CentaurJpaAuditorAware();
  }
}
