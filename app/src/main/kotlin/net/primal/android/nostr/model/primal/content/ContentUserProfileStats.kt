package net.primal.android.nostr.model.primal.content

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentUserProfileStats(
    // TODO Update after backend confirmation
    @SerialName("pubkey") val profileId: String? = null,
    @SerialName("follows_count") val followsCount: Int,
    @SerialName("followers_count") val followersCount: Int,
    @SerialName("note_count") val noteCount: Int,
    @SerialName("time_joined") val timeJoined: Long?,
)
