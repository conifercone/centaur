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

package com.sky.centaur.extension.distributed.lock.zookeeper;

import com.sky.centaur.basis.exception.CentaurException;
import com.sky.centaur.basis.response.ResultCode;
import com.sky.centaur.extension.distributed.lock.DistributedLock;
import org.apache.curator.framework.recipes.locks.InterProcessLock;

/**
 * zookeeper分布式锁实现
 *
 * @author kaiyu.shan
 * @since 2024-03-06
 */
public class ZookeeperDistributedLockImpl implements DistributedLock {

  private final InterProcessLock interProcessLock;

  public ZookeeperDistributedLockImpl(InterProcessLock interProcessLock) {
    this.interProcessLock = interProcessLock;
  }

  @Override
  public void lock() {
    try {
      interProcessLock.acquire();
    } catch (Exception e) {
      throw new CentaurException(ResultCode.FAILED_TO_OBTAIN_DISTRIBUTED_LOCK);
    }
  }

  @Override
  public void unlock() {
    try {
      interProcessLock.release();
    } catch (Exception e) {
      throw new CentaurException(ResultCode.FAILED_TO_RELEASE_DISTRIBUTED_LOCK);
    }
  }
}
