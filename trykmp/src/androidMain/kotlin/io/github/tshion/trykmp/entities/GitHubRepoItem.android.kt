package io.github.tshion.trykmp.entities

import io.github.tshion.trykmp.UtcDate
import io.github.tshion.trykmp.toUtcDate
import io.github.tshion.trykmp.webdto.RepoSearchResultItemDto

@ConsistentCopyVisibility
public data class GitHubRepoItem private constructor(
    val fullName: String,
    val description: String?,
    val url: String,
    val updatedAt: UtcDate,
    val language: String?,
) {
    internal constructor(
        dto: RepoSearchResultItemDto,
    ) : this(
        dto.full_name,
        dto.description,
        dto.html_url,
        dto.updated_at.toUtcDate(),
        dto.language
    )
}
