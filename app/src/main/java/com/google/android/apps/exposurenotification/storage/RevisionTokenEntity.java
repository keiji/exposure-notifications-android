/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.google.android.apps.exposurenotification.storage;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.auto.value.AutoValue;
import com.google.auto.value.AutoValue.CopyAnnotations;

/**
 * A TEK revision token received from the key server.
 *
 * <p>This token grants the this app the ability to revise the test type associated with previously
 * uploaded TEKs, say, to change from "likely diagnosis" to "negative".
 */
@AutoValue
@Entity
public abstract class RevisionTokenEntity {

  @CopyAnnotations
  @PrimaryKey(autoGenerate = true)
  public abstract long getId();

  public abstract long getCreatedTimestampMs();

  @CopyAnnotations
  @NonNull
  public abstract String getRevisionToken();

  /**
   * Creates a {@link RevisionTokenEntity} with all fields populated.
   *
   * <p>The {@code id} param is required by Room but we ignore it and use zero instead so that the
   * ID will be autogenerated.
   */
  public static RevisionTokenEntity create(
      long id, long createdTimestampMs, @NonNull String revisionToken) {
    // @AutoValue wants an ID value, but to get Room to autogenerate the ID, we set it to zero.
    return new AutoValue_RevisionTokenEntity(0L, createdTimestampMs, revisionToken);
  }
}