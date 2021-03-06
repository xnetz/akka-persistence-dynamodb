/*
 * Copyright 2019 Junichi Kato
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
package com.github.j5ik2o.akka.persistence.dynamodb.utils

import java.time.{ Duration => JavaDuration }

import com.github.j5ik2o.akka.persistence.dynamodb.config.DynamoDBClientConfig
import software.amazon.awssdk.http.Protocol
import software.amazon.awssdk.http.nio.netty.{ NettyNioAsyncHttpClient, SdkEventLoopGroup }

object HttpClientBuilderUtils {

  def setup(clientConfig: DynamoDBClientConfig): NettyNioAsyncHttpClient.Builder = {
    val result = NettyNioAsyncHttpClient.builder()
    clientConfig.maxConcurrency.foreach(v => result.maxConcurrency(v))
    clientConfig.maxPendingConnectionAcquires.foreach(v => result.maxPendingConnectionAcquires(v))
    clientConfig.readTimeout.foreach(v => result.readTimeout(JavaDuration.ofMillis(v.toMillis)))
    clientConfig.writeTimeout.foreach(v => result.writeTimeout(JavaDuration.ofMillis(v.toMillis)))
    clientConfig.connectionTimeout.foreach(v => result.connectionTimeout(JavaDuration.ofMillis(v.toMillis)))
    clientConfig.connectionAcquisitionTimeout.foreach(v =>
      result.connectionAcquisitionTimeout(JavaDuration.ofMillis(v.toMillis))
    )
    clientConfig.connectionTimeToLive.foreach(v => result.connectionTimeToLive(JavaDuration.ofMillis(v.toMillis)))
    clientConfig.maxIdleConnectionTimeout.foreach(v => result.connectionMaxIdleTime(JavaDuration.ofMillis(v.toMillis)))
    clientConfig.useConnectionReaper.foreach(v => result.useIdleConnectionReaper(v))
    clientConfig.userHttp2.foreach(v => if (v) result.protocol(Protocol.HTTP2) else result.protocol(Protocol.HTTP1_1))
    clientConfig.maxHttp2Streams.foreach(v => result.maxHttp2Streams(v))
    clientConfig.threadsOfEventLoopGroup.foreach(v =>
      result.eventLoopGroup(SdkEventLoopGroup.builder().numberOfThreads(v).build())
    )
    result
  }

}
