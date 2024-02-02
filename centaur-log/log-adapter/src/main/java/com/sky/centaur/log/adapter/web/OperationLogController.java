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
package com.sky.centaur.log.adapter.web;

import com.sky.centaur.log.client.api.OperationLogService;
import com.sky.centaur.log.client.dto.OperationLogSubmitCmd;
import com.sky.centaur.log.client.dto.co.OperationLogQryCo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志相关
 *
 * @author 单开宇
 * @since 2024-01-25
 */
@RestController
@RequestMapping("/operation")
@Tag(name = "操作日志管理")
public class OperationLogController {

  @Resource
  private OperationLogService operationLogService;

  @Operation(summary = "提交日志")
  @PostMapping("/submit")
  @ResponseBody
  @API(status = Status.STABLE)
  public void submit(@RequestBody OperationLogSubmitCmd operationLogSubmitCmd) {
    operationLogService.submit(operationLogSubmitCmd);
  }

  @Operation(summary = "根据日志ID获取操作日志")
  @GetMapping("/findById")
  @ResponseBody
  @API(status = Status.STABLE)
  public OperationLogQryCo findOperationLogById(@RequestParam(value = "id") String id) {
    return operationLogService.findOperationLogById(id);
  }

}
