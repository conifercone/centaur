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
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 * 权限领域网关
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
public interface AuthorityGateway {

  /**
   * 添加权限
   *
   * @param authority 权限信息
   */
  void add(Authority authority);

  /**
   * 根据id删除权限
   *
   * @param id 权限id
   */
  void deleteById(Long id);

  /**
   * 根据id更新权限
   *
   * @param authority 目标权限信息
   */
  void updateById(Authority authority);

  /**
   * 分页查询权限
   *
   * @param authority 查询条件
   * @param pageNo    页码
   * @param pageSize  每页数量
   * @return 查询结果
   */
  Page<Authority> findAll(Authority authority, int pageNo, int pageSize);

  /**
   * 根据id查询权限详情
   *
   * @param id 权限id
   * @return 权限信息
   */
  Optional<Authority> findById(Long id);
}
