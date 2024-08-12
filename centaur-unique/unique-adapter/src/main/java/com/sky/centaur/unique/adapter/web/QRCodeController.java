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

import com.sky.centaur.basis.constants.CommonConstants;
import com.sky.centaur.basis.response.ResultResponse;
import com.sky.centaur.unique.client.api.QRCodeService;
import com.sky.centaur.unique.client.dto.QRCodeGenerateCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Base64;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二维码相关接口
 *
 * @author kaiyu.shan
 * @since 1.0.4
 */
@RestController
@RequestMapping("/qrCode")
@Tag(name = "二维码管理")
public class QRCodeController {

  private final QRCodeService qrCodeService;

  @Autowired
  public QRCodeController(QRCodeService qrCodeService) {
    this.qrCodeService = qrCodeService;
  }

  @Operation(summary = "生成二维码（返回Base64格式的图片数据链接）")
  @GetMapping("/dataUrl")
  @ResponseBody
  @API(status = Status.STABLE, since = "1.0.4")
  public ResultResponse<String> dataUrlGenerate(
      @RequestBody @Valid QRCodeGenerateCmd qrCodeGenerateCmd) {
    return ResultResponse.success(String.format(CommonConstants.DATA_URL_TEMPLATE,
        qrCodeGenerateCmd.getQrCodeGenerateCo().getImageFormat().getMimeType(),
        Base64.getEncoder().encodeToString(qrCodeService.generate(qrCodeGenerateCmd))));
  }
}
