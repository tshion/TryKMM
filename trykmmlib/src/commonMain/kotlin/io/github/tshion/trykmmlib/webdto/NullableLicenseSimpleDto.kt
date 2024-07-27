package io.github.tshion.trykmmlib.webdto

import kotlinx.serialization.Serializable

/**
 * License Simple
 *
 * @param key
 * @param name
 * @param url
 * @param spdx_id
 * @param node_id
 * @param html_url
 */
@Serializable
internal data class NullableLicenseSimpleDto(
    val key: String,
    val name: String,
    val url: String?,
    val spdx_id: String?,
    val node_id: String,
    val html_url: String? = null
)
