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
package com.sky.centaur.unique.infrastructure.barcode.convertor;

import com.sky.centaur.unique.client.dto.co.BarCodeGenerateCo;
import com.sky.centaur.unique.domain.barcode.BarCode;
import java.util.Optional;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Component;

/**
 * 条形码对象转换类
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.4
 */
@Component
public class BarCodeConvertor {

  @Contract("_ -> new")
  @API(status = Status.STABLE, since = "1.0.4")
  public Optional<BarCode> toEntity(
      BarCodeGenerateCo barCodeGenerateCo) {
    return Optional.ofNullable(barCodeGenerateCo).map(BarCodeMapper.INSTANCE::toEntity);
  }
}
