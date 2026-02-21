package io.github.tshion.trykmp.entities

import io.github.tshion.trykmp.webdto.GetSearchRepositoriesResponseDto

@ConsistentCopyVisibility
public data class GitHubRepo private constructor(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GitHubRepoItem>,
) {
    internal constructor(
        dto: GetSearchRepositoriesResponseDto,
    ) : this(
        dto.total_count,
        dto.incomplete_results,
        dto.items.map { GitHubRepoItem(it) },
    )
}
