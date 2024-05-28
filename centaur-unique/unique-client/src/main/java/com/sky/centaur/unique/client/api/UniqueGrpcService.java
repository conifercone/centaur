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
package com.sky.centaur.unique.client.api;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.micrometer.core.instrument.binder.grpc.ObservationGrpcClientInterceptor;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

/**
 * 唯一性数据生成grpc服务
 *
 * @author kaiyu.shan
 * @since 1.0.0
 */
class UniqueGrpcService {

  private final DiscoveryClient consulDiscoveryClient;
  private final ObservationGrpcClientInterceptor observationGrpcClientInterceptor;

  public UniqueGrpcService(DiscoveryClient consulDiscoveryClient,
      @NotNull ObjectProvider<ObservationGrpcClientInterceptor> grpcClientInterceptorObjectProvider) {
    this.consulDiscoveryClient = consulDiscoveryClient;
    this.observationGrpcClientInterceptor = grpcClientInterceptorObjectProvider.getIfAvailable();
  }

  protected Optional<ManagedChannel> getManagedChannelUsePlaintext() {
    return getServiceInstance().map(
        serviceInstance -> {
          ManagedChannelBuilder<?> builder = ManagedChannelBuilder.forAddress(
                  serviceInstance.getHost(),
                  serviceInstance.getPort())
              .usePlaintext();
          Optional.ofNullable(observationGrpcClientInterceptor).ifPresent(builder::intercept);
          return builder.build();
        });
  }

  protected Optional<ServiceInstance> getServiceInstance() {
    List<ServiceInstance> instances = consulDiscoveryClient.getInstances("grpc-unique");
    return Optional.ofNullable(instances).flatMap(is -> is.stream().findFirst());
  }

}
