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
package com.sky.centaur.authentication.domain.authority.gateway;

import com.sky.centaur.authentication.domain.authority.Authority;
import org.springframework.data.domain.Page;

/**
 * 权限领域网关
 *
 * @author 单开宇
 * @since 2024-01-16
 */
public interface AuthorityGateway {

  void add(Authority authority);

  void delete(Authority authority);

  void updateById(Authority authority);

  Page<Authority> findAll(Authority authority, int pageNo, int pageSize);
}
