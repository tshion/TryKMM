package io.github.tshion.trykmp.webdto

import kotlinx.serialization.Serializable

@Serializable
internal data class GitHubErrorItemDto(
    val resource: String,
    val field: String,
    val code: String,
)
