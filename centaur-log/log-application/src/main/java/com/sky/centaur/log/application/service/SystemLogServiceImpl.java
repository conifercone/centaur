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
package com.sky.centaur.log.application.service;

import com.sky.centaur.log.application.system.executor.SystemLogFindAllCmdExe;
import com.sky.centaur.log.application.system.executor.SystemLogSaveCmdExe;
import com.sky.centaur.log.application.system.executor.SystemLogSubmitCmdExe;
import com.sky.centaur.log.client.api.SystemLogService;
import com.sky.centaur.log.client.api.grpc.SystemLogServiceEmptyResult;
import com.sky.centaur.log.client.api.grpc.SystemLogServiceGrpc.SystemLogServiceImplBase;
import com.sky.centaur.log.client.api.grpc.SystemLogSubmitGrpcCmd;
import com.sky.centaur.log.client.api.grpc.SystemLogSubmitGrpcCo;
import com.sky.centaur.log.client.dto.SystemLogFindAllCmd;
import com.sky.centaur.log.client.dto.SystemLogSaveCmd;
import com.sky.centaur.log.client.dto.SystemLogSubmitCmd;
import com.sky.centaur.log.client.dto.co.SystemLogFindAllCo;
import com.sky.centaur.log.client.dto.co.SystemLogSubmitCo;
import io.grpc.stub.StreamObserver;
import io.micrometer.core.instrument.binder.grpc.ObservationGrpcServerInterceptor;
import org.jetbrains.annotations.NotNull;
import org.lognet.springboot.grpc.GRpcService;
import org.lognet.springboot.grpc.recovery.GRpcRuntimeExceptionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
@Service
@GRpcService(interceptors = {ObservationGrpcServerInterceptor.class})
public class SystemLogServiceImpl extends SystemLogServiceImplBase implements SystemLogService {

  private final SystemLogSubmitCmdExe systemLogSubmitCmdExe;

  private final SystemLogSaveCmdExe systemLogSaveCmdExe;
  private final SystemLogFindAllCmdExe systemLogFindAllCmdExe;

  @Autowired
  public SystemLogServiceImpl(SystemLogSubmitCmdExe systemLogSubmitCmdExe,
      SystemLogSaveCmdExe systemLogSaveCmdExe, SystemLogFindAllCmdExe systemLogFindAllCmdExe) {
    this.systemLogSubmitCmdExe = systemLogSubmitCmdExe;
    this.systemLogSaveCmdExe = systemLogSaveCmdExe;
    this.systemLogFindAllCmdExe = systemLogFindAllCmdExe;
  }

  @Override
  public void submit(SystemLogSubmitCmd systemLogSubmitCmd) {
    systemLogSubmitCmdExe.execute(systemLogSubmitCmd);
  }

  @Override
  public void save(SystemLogSaveCmd systemLogSaveCmd) {
    systemLogSaveCmdExe.execute(systemLogSaveCmd);
  }

  @Override
  public Page<SystemLogFindAllCo> findAll(SystemLogFindAllCmd systemLogFindAllCmd) {
    return systemLogFindAllCmdExe.execute(systemLogFindAllCmd);
  }

  @Override
  public void submit(SystemLogSubmitGrpcCmd request,
      StreamObserver<SystemLogServiceEmptyResult> responseObserver) {
    SystemLogSubmitCmd systemLogSubmitCmd = new SystemLogSubmitCmd();
    SystemLogSubmitCo systemLogSubmitCo = getSystemLogSubmitCo(
        request);
    systemLogSubmitCmd.setSystemLogSubmitCo(systemLogSubmitCo);
    try {
      systemLogSubmitCmdExe.execute(systemLogSubmitCmd);
    } catch (Exception e) {
      throw new GRpcRuntimeExceptionWrapper(e);
    }
    SystemLogServiceEmptyResult build = SystemLogServiceEmptyResult.newBuilder().build();
    responseObserver.onNext(build);
    responseObserver.onCompleted();
  }

  @NotNull
  private static SystemLogSubmitCo getSystemLogSubmitCo(
      @NotNull SystemLogSubmitGrpcCmd request) {
    SystemLogSubmitCo systemLogSubmitCo = new SystemLogSubmitCo();
    SystemLogSubmitGrpcCo systemLogSubmitGrpcCo = request.getSystemLogSubmitCo();
    systemLogSubmitCo.setContent(systemLogSubmitGrpcCo.getContent());
    systemLogSubmitCo.setCategory(systemLogSubmitGrpcCo.getCategory());
    systemLogSubmitCo.setSuccess(systemLogSubmitGrpcCo.getSuccess());
    systemLogSubmitCo.setFail(systemLogSubmitGrpcCo.getFail());
    return systemLogSubmitCo;
  }
}
