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

package com.sky.centaur.basis.dataobject.jpa;

import com.sky.centaur.basis.dataobject.BasisDataObject;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * jpa基础数据对象
 *
 * @author 单开宇
 * @since 2024-02-26
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class JpaBasisDataObject extends BasisDataObject {

  @CreationTimestamp
  @Column(name = "creation_time", updatable = false)
  private OffsetDateTime creationTime;

  @CreatedBy
  @Column(name = "founder", updatable = false)
  private Long founder;

  @LastModifiedBy
  @Column(name = "modifier")
  private Long modifier;

  @UpdateTimestamp
  @Column(name = "modification_time")
  private OffsetDateTime modificationTime;

  @Override
  public Long getFounder() {
    return founder;
  }

  @Override
  public Long getModifier() {
    return modifier;
  }

  @Override
  public OffsetDateTime getCreationTime() {
    return creationTime;
  }

  @Override
  public OffsetDateTime getModificationTime() {
    return modificationTime;
  }

  @Override
  public void setFounder(Long founder) {
    this.founder = founder;
  }

  @Override
  public void setModifier(Long modifier) {
    this.modifier = modifier;
  }

  @Override
  public void setCreationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
  }

  @Override
  public void setModificationTime(OffsetDateTime modificationTime) {
    this.modificationTime = modificationTime;
  }
}