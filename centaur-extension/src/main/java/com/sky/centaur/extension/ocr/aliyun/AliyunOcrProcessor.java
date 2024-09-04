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
package com.sky.centaur.extension.ocr.aliyun;

import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeBasicRequest;
import com.aliyun.ocr_api20210707.models.RecognizeBasicResponse;
import com.aliyun.teautil.models.RuntimeOptions;
import com.sky.centaur.extension.ocr.Ocr;
import com.sky.centaur.extension.ocr.OcrProcessor;
import java.io.FileInputStream;

/**
 * 阿里云ocr处理器
 *
 * @author <a href="mailto:kaiyu.shan@outlook.com">kaiyu.shan</a>
 * @since 1.0.5
 */
public class AliyunOcrProcessor implements OcrProcessor {

  private final Client client;

  public AliyunOcrProcessor(Client client) {
    this.client = client;
  }

  @Override
  public String doOcr(Ocr ocr) {
    try {
      RecognizeBasicRequest recognizeBasicRequest = new RecognizeBasicRequest()
          .setBody(new FileInputStream(ocr.getSourceFile()))
          .setNeedRotate(false);
      RecognizeBasicResponse recognizeBasicResponse = client.recognizeBasicWithOptions(
          recognizeBasicRequest, new RuntimeOptions());
      return recognizeBasicResponse.getBody().getData();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}