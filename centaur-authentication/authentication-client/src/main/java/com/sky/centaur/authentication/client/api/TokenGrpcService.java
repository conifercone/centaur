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
package com.sky.centaur.authentication.client.api;

import com.sky.centaur.authentication.client.api.grpc.TokenServiceGrpc;
import com.sky.centaur.authentication.client.api.grpc.TokenServiceGrpc.TokenServiceFutureStub;
import com.sky.centaur.authentication.client.api.grpc.TokenValidityGrpcCmd;
import com.sky.centaur.authentication.client.api.grpc.TokenValidityGrpcCo;
import io.grpc.ManagedChannel;
import io.micrometer.core.instrument.binder.grpc.ObservationGrpcClientInterceptor;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.discovery.DiscoveryClient;

/**
 * token对外提供grpc调用实例
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
public class TokenGrpcService extends AuthenticationGrpcService implements DisposableBean {

  private ManagedChannel channel;

  private static final Logger LOGGER = LoggerFactory.getLogger(TokenGrpcService.class);

  public TokenGrpcService(
      DiscoveryClient discoveryClient,
      ObjectProvider<ObservationGrpcClientInterceptor> grpcClientInterceptorObjectProvider) {
    super(discoveryClient, grpcClientInterceptorObjectProvider);
  }

  @Override
  public void destroy() {
    Optional.ofNullable(channel).ifPresent(ManagedChannel::shutdown);
  }

  @API(status = Status.STABLE, since = "1.0.0")
  public TokenValidityGrpcCo validity(TokenValidityGrpcCmd tokenValidityGrpcCmd) {
    if (channel == null) {
      return getManagedChannelUsePlaintext().map(managedChannel -> {
        channel = managedChannel;
        return extracted(tokenValidityGrpcCmd);
      }).orElse(null);
    } else {
      return extracted(tokenValidityGrpcCmd);
    }
  }

  private @Nullable TokenValidityGrpcCo extracted(TokenValidityGrpcCmd tokenValidityGrpcCmd) {
    TokenServiceFutureStub tokenServiceFutureStub = TokenServiceGrpc.newFutureStub(channel);
    try {
      return tokenServiceFutureStub.validity(tokenValidityGrpcCmd).get(3, TimeUnit.SECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      LOGGER.error(e.getMessage());
      return null;
    }
  }

}
