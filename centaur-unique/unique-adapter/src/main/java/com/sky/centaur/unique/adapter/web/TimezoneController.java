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
package com.sky.centaur.unique.adapter.web;

import com.sky.centaur.basis.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.ZoneId;
import java.util.Set;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 时区相关接口
 *
 * @author kaiyu.shan
 * @since 1.0.1
 */
@RestController
@RequestMapping("/timezone")
@Tag(name = "时区管理")
public class TimezoneController {

  @Operation(summary = "获取可用时区列表")
  @GetMapping("/available")
  @ResponseBody
  @API(status = Status.STABLE, since = "1.0.1")
  public ResultResponse<Set<String>> available() {
    return ResultResponse.success(ZoneId.getAvailableZoneIds());
  }
}
