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
package com.github.j5ik2o.akka.persistence.dynamodb.config

import com.github.j5ik2o.akka.persistence.dynamodb.const.DefaultColumnsDef
import com.github.j5ik2o.akka.persistence.dynamodb.utils.ConfigOps._
import com.typesafe.config.Config

object JournalColumnsDefConfig {

  def fromConfig(config: Config): JournalColumnsDefConfig = {
    JournalColumnsDefConfig(
      partitionKeyColumnName = config.asString("partition-key-column-name", DefaultColumnsDef.PartitionKey),
      persistenceIdColumnName =
        config.asString("persistence-id-column-name", DefaultColumnsDef.PersistenceIdColumnName),
      sequenceNrColumnName = config.asString("sequence-nr-column-name", DefaultColumnsDef.SequenceNrColumnName),
      deletedColumnName = config.asString("deleted-column-name", DefaultColumnsDef.DeletedColumnName),
      messageColumnName = config.asString("message-column-name", DefaultColumnsDef.MessageColumnName),
      orderingColumnName = config.asString("ordering-column-name", DefaultColumnsDef.OrderingColumnName),
      timestampColumnName = config.asString("timestamp-column-name", DefaultColumnsDef.TimestampColumnName),
      tagsColumnName = config.asString("tags-column-name", DefaultColumnsDef.TagsColumnName)
    )
  }

}

case class JournalColumnsDefConfig(
    partitionKeyColumnName: String,
    persistenceIdColumnName: String,
    sequenceNrColumnName: String,
    deletedColumnName: String,
    messageColumnName: String,
    orderingColumnName: String,
    timestampColumnName: String,
    tagsColumnName: String
)
