/*
 * Copyright 2020 The Matrix.org Foundation C.I.C.
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
 *
 */

package org.matrix.android.sdk.api.session.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import org.matrix.android.sdk.api.MatrixCallback
import org.matrix.android.sdk.api.session.identity.ThreePid
import org.matrix.android.sdk.api.util.Cancelable
import org.matrix.android.sdk.api.util.JsonDict
import org.matrix.android.sdk.api.util.Optional

/**
 * This interface defines methods to handling profile information. It's implemented at the session level.
 */
interface ProfileService {

    companion object Constants {
        const val DISPLAY_NAME_KEY = "displayname"
        const val AVATAR_URL_KEY = "avatar_url"
    }

    /**
     * Return the current display name for this user
     * @param userId the userId param to look for
     *
     */
    fun getDisplayName(userId: String, matrixCallback: MatrixCallback<Optional<String>>): Cancelable

    /**
     * Update the display name for this user
     * @param userId the userId to update the display name of
     * @param newDisplayName the new display name of the user
     */
    fun setDisplayName(userId: String, newDisplayName: String, matrixCallback: MatrixCallback<Unit>): Cancelable

    /**
     * Update the avatar for this user
     * @param userId the userId to update the avatar of
     * @param newAvatarUri the new avatar uri of the user
     * @param fileName the fileName of selected image
     */
    fun updateAvatar(userId: String, newAvatarUri: Uri, fileName: String, matrixCallback: MatrixCallback<Unit>): Cancelable

    /**
     * Return the current avatarUrl for this user.
     * @param userId the userId param to look for
     *
     */
    fun getAvatarUrl(userId: String, matrixCallback: MatrixCallback<Optional<String>>): Cancelable

    /**
     * Get the combined profile information for this user.
     * This may return keys which are not limited to displayname or avatar_url.
     * @param userId the userId param to look for
     *
     */
    fun getProfile(userId: String, matrixCallback: MatrixCallback<JsonDict>): Cancelable

    /**
     * Get the current user 3Pids
     */
    fun getThreePids(): List<ThreePid>

    /**
     * Get the current user 3Pids Live
     * @param refreshData set to true to fetch data from the homeserver
     */
    fun getThreePidsLive(refreshData: Boolean): LiveData<List<ThreePid>>

    /**
     * Get the pending 3Pids, i.e. ThreePids that have requested a token, but not yet validated by the user.
     */
    fun getPendingThreePids(): List<ThreePid>

    /**
     * Get the pending 3Pids Live
     */
    fun getPendingThreePidsLive(): LiveData<List<ThreePid>>

    /**
     * Add a 3Pids. This is the first step to add a ThreePid to an account. Then the threePid will be added to the pending threePid list.
     */
    fun addThreePid(threePid: ThreePid, matrixCallback: MatrixCallback<Unit>): Cancelable

    /**
     * Validate a code received by text message
     */
    fun submitSmsCode(threePid: ThreePid.Msisdn, code: String, matrixCallback: MatrixCallback<Unit>): Cancelable

    /**
     * Finalize adding a 3Pids. Call this method once the user has validated that he owns the ThreePid
     */
    fun finalizeAddingThreePid(threePid: ThreePid,
                               uiaSession: String?,
                               accountPassword: String?,
                               matrixCallback: MatrixCallback<Unit>): Cancelable

    /**
     * Cancel adding a threepid. It will remove locally stored data about this ThreePid
     */
    fun cancelAddingThreePid(threePid: ThreePid,
                             matrixCallback: MatrixCallback<Unit>): Cancelable

    /**
     * Remove a 3Pid from the Matrix account.
     */
    fun deleteThreePid(threePid: ThreePid, matrixCallback: MatrixCallback<Unit>): Cancelable
}
