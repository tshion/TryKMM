package io.github.tshion.trykmmlib.webdto

import kotlinx.serialization.Serializable

/**
 * @param total_count
 * @param incomplete_results
 * @param items
 */
@Serializable
internal data class GetSearchRepositoriesResponseDto(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepoSearchResultItemDto>,
)
