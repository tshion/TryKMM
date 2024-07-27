package io.github.tshion.trykmmlib.webdto

import kotlinx.serialization.Serializable

@Serializable
internal data class SearchResultTextMatchesDto(
    val objectUrl: String? = null,
    val objectType: String? = null,
    val property: String? = null,
    val fragment: String? = null,
    val matches: List<MatchesDto>? = null,
)
