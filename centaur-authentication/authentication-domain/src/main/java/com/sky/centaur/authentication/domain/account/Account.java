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
package com.sky.centaur.authentication.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sky.centaur.authentication.domain.authority.Authority;
import com.sky.centaur.authentication.domain.role.Role;
import com.sky.centaur.basis.domain.BasisDomainModel;
import com.sky.centaur.basis.enums.SexEnum;
import java.util.ArrayList;
import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 账户领域模型
 *
 * @author 单开宇
 * @since 2024-01-16
 */
@JsonDeserialize
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account extends BasisDomainModel implements UserDetails {

  @Getter
  private Long id;

  private String username;

  private String password;

  private Boolean enabled = false;

  private Boolean credentialsNonExpired = true;

  private Boolean accountNonLocked = true;

  private Boolean accountNonExpired = true;

  @Getter
  private Role role;

  @Getter
  @Setter
  private String avatarUrl;

  @Getter
  @Setter
  private String phone;

  @Getter
  @Setter
  private SexEnum sex;

  public Account(Long id, String username, String password, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public Account(Long id, String username, String password, boolean enabled,
      boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.accountNonExpired = accountNonExpired;
    this.credentialsNonExpired = credentialsNonExpired;
    this.role = role;
    this.accountNonLocked = accountNonLocked;
  }

  @Override
  public Collection<Authority> getAuthorities() {
    return this.role.getAuthorities();
  }

  public void setAuthorities(Collection<Authority> authorities) {
    this.role.setAuthorities(new ArrayList<>(authorities));
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }
}
