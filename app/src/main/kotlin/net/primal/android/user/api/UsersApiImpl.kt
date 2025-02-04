package net.primal.android.user.api

import javax.inject.Inject
import kotlinx.serialization.encodeToString
import net.primal.android.core.serialization.json.NostrJson
import net.primal.android.explore.api.model.UsersResponse
import net.primal.android.networking.di.PrimalCacheApiClient
import net.primal.android.networking.primal.PrimalApiClient
import net.primal.android.networking.primal.PrimalCacheFilter
import net.primal.android.networking.primal.PrimalVerb
import net.primal.android.nostr.model.NostrEventKind
import net.primal.android.user.api.model.FollowListRequestBody
import net.primal.android.user.api.model.UserContactsResponse
import net.primal.android.user.api.model.UserProfileResponse
import net.primal.android.user.api.model.UserProfilesRequestBody
import net.primal.android.user.api.model.UserProfilesResponse
import net.primal.android.user.api.model.UserRelaysResponse
import net.primal.android.user.api.model.UserRequestBody

class UsersApiImpl @Inject constructor(
    @PrimalCacheApiClient private val primalApiClient: PrimalApiClient,
) : UsersApi {

    override suspend fun getUserProfile(userId: String): UserProfileResponse {
        val queryResult = primalApiClient.query(
            message = PrimalCacheFilter(
                primalVerb = PrimalVerb.USER_PROFILE,
                optionsJson = NostrJson.encodeToString(UserRequestBody(pubkey = userId)),
            ),
        )

        return UserProfileResponse(
            metadata = queryResult.findNostrEvent(NostrEventKind.Metadata),
            profileStats = queryResult.findPrimalEvent(NostrEventKind.PrimalUserProfileStats),
            cdnResources = queryResult.filterPrimalEvents(NostrEventKind.PrimalCdnResource),
        )
    }

    override suspend fun getUserFollowList(userId: String): UserContactsResponse {
        val queryResult = primalApiClient.query(
            message = PrimalCacheFilter(
                primalVerb = PrimalVerb.FOLLOW_LIST,
                optionsJson = NostrJson.encodeToString(
                    FollowListRequestBody(pubkey = userId, extendedResponse = false),
                ),
            ),
        )

        return UserContactsResponse(
            followListEvent = queryResult.findNostrEvent(NostrEventKind.FollowList),
            followMetadataList = queryResult.filterNostrEvents(NostrEventKind.Metadata),
            userScores = queryResult.findPrimalEvent(NostrEventKind.PrimalUserScores),
            cdnResources = queryResult.filterPrimalEvents(NostrEventKind.PrimalCdnResource),
        )
    }

    override suspend fun getUserProfilesMetadata(userIds: Set<String>): UserProfilesResponse {
        val queryResult = primalApiClient.query(
            message = PrimalCacheFilter(
                primalVerb = PrimalVerb.USER_INFOS,
                optionsJson = NostrJson.encodeToString(
                    UserProfilesRequestBody(userIds = userIds),
                ),
            ),
        )

        return UserProfilesResponse(
            metadataEvents = queryResult.filterNostrEvents(NostrEventKind.Metadata),
            cdnResources = queryResult.filterPrimalEvents(NostrEventKind.PrimalCdnResource),
            userScores = queryResult.findPrimalEvent(NostrEventKind.PrimalUserScores),
        )
    }

    override suspend fun getUserFollowing(userId: String): UsersResponse {
        val queryResult = primalApiClient.query(
            message = PrimalCacheFilter(
                primalVerb = PrimalVerb.FOLLOW_LIST,
                optionsJson = NostrJson.encodeToString(UserRequestBody(pubkey = userId)),
            ),
        )

        return UsersResponse(
            contactsMetadata = queryResult.filterNostrEvents(NostrEventKind.Metadata),
            followerCounts = queryResult.findPrimalEvent(NostrEventKind.PrimalUserFollowersCounts),
            userScores = queryResult.findPrimalEvent(NostrEventKind.PrimalUserScores),
            cdnResources = queryResult.filterPrimalEvents(NostrEventKind.PrimalCdnResource),
        )
    }

    override suspend fun getUserFollowers(userId: String): UsersResponse {
        val queryResult = primalApiClient.query(
            message = PrimalCacheFilter(
                primalVerb = PrimalVerb.USER_FOLLOWERS,
                optionsJson = NostrJson.encodeToString(UserRequestBody(pubkey = userId)),
            ),
        )

        return UsersResponse(
            contactsMetadata = queryResult.filterNostrEvents(NostrEventKind.Metadata),
            followerCounts = queryResult.findPrimalEvent(NostrEventKind.PrimalUserFollowersCounts),
            userScores = queryResult.findPrimalEvent(NostrEventKind.PrimalUserScores),
            cdnResources = queryResult.filterPrimalEvents(NostrEventKind.PrimalCdnResource),
        )
    }

    override suspend fun getUserRelays(userId: String): UserRelaysResponse {
        val queryResult = primalApiClient.query(
            message = PrimalCacheFilter(
                primalVerb = PrimalVerb.USER_RELAYS,
                optionsJson = NostrJson.encodeToString(UserRequestBody(pubkey = userId)),
            ),
        )

        return UserRelaysResponse(
            cachedRelayListEvent = queryResult.findPrimalEvent(NostrEventKind.PrimalUserRelaysList),
        )
    }
}
