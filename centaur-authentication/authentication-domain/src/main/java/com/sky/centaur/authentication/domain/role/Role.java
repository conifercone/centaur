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

package com.sky.centaur.authentication.domain.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sky.centaur.authentication.domain.authority.Authority;
import com.sky.centaur.basis.domain.BasisDomainModel;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色领域模型
 *
 * @author 单开宇
 * @since 2024-02-23
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Role extends BasisDomainModel {

  private Long id;
  private String code;
  private String name;
  private List<Authority> authorities;

  /**
   * all properties constructor
   */
  public Role(Long id, String code, String name, List<Authority> authorities) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.authorities = authorities;
  }

}
