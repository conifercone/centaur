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
package com.sky.centaur.file.adapter.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.centaur.file.client.api.StreamFileService;
import com.sky.centaur.file.client.dto.StreamFileDownloadCmd;
import com.sky.centaur.file.client.dto.StreamFileUploadCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 流式文件管理
 *
 * @author kaiyu.shan
 * @since 1.0.1
 */
@RestController
@RequestMapping("/stream")
@Tag(name = "流式文件管理")
public class StreamFileController {

  private final StreamFileService streamFileService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  public StreamFileController(StreamFileService streamFileService) {
    this.streamFileService = streamFileService;
  }

  @Operation(summary = "文件上传")
  @PostMapping("/upload")
  @ResponseBody
  @API(status = Status.STABLE, since = "1.0.1")
  public void add(@RequestParam("streamFileUploadCmd") String streamFileUploadCmd,
      @RequestParam("file") MultipartFile file) throws JsonProcessingException {
    StreamFileUploadCmd fileUploadCmd = objectMapper.readValue(streamFileUploadCmd,
        StreamFileUploadCmd.class);
    fileUploadCmd.getStreamFileUploadCo().setContent(file);
    streamFileService.uploadFile(fileUploadCmd);
  }

  @Operation(summary = "文件下载")
  @GetMapping("/download")
  @ResponseBody
  @API(status = Status.STABLE, since = "1.0.1")
  public void download(@RequestBody StreamFileDownloadCmd streamFileDownloadCmd,
      HttpServletResponse response)
      throws IOException {
    Assert.notNull(streamFileDownloadCmd, "StreamFileDownloadCmd cannot be null");
    Assert.notNull(streamFileDownloadCmd.getStreamFileDownloadCo(),
        "StreamFileDownloadCo cannot be null");
    response.setHeader("Content-Disposition",
        "attachment;filename=" + (ObjectUtils.isEmpty(
            streamFileDownloadCmd.getStreamFileDownloadCo().getRename())
            ? streamFileDownloadCmd.getStreamFileDownloadCo().getName()
            : streamFileDownloadCmd.getStreamFileDownloadCo()
                .getRename()));
    response.setContentType("application/force-download");
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    IOUtils.copy(streamFileService.download(streamFileDownloadCmd), response.getOutputStream());

  }
}
