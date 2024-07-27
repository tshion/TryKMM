package io.github.tshion.trykmmlib.webdto

import kotlinx.serialization.Serializable

@Serializable
internal data class MatchesDto(
    val text: String? = null,
    val indices: List<Int>? = null,
)
