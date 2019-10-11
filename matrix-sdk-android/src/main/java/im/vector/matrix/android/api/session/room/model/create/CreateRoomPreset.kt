/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.matrix.android.api.session.room.model.create

import com.squareup.moshi.Json

enum class CreateRoomPreset {
    @Json(name = "private_chat")
    PRESET_PRIVATE_CHAT,

    @Json(name = "public_chat")
    PRESET_PUBLIC_CHAT,

    @Json(name = "trusted_private_chat")
    PRESET_TRUSTED_PRIVATE_CHAT
}
