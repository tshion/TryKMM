package io.github.tshion.trykmmlib.webdto

import kotlinx.serialization.Serializable

/**
 * A GitHub user.
 *
 * @param name
 * @param email
 * @param login
 * @param id
 * @param node_id
 * @param avatar_url
 * @param gravatar_id
 * @param url
 * @param html_url
 * @param followers_url
 * @param following_url
 * @param gists_url
 * @param starred_url
 * @param subscriptions_url
 * @param organizations_url
 * @param repos_url
 * @param events_url
 * @param received_events_url
 * @param type
 * @param site_admin
 * @param starred_at
 */
@Serializable
internal data class NullableSimpleUserDto(
    val name: String? = null,
    val email: String? = null,
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String?,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,
    val starred_at: String? = null,
)
