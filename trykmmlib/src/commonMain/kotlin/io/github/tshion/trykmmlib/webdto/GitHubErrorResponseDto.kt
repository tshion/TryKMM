package io.github.tshion.trykmmlib.webdto

import kotlinx.serialization.Serializable

/**
 * GitHub REST API のエラーレスポンス
 *
 * ※[参考文献](https://docs.github.com/ja/rest/overview/resources-in-the-rest-api?apiVersion=2022-11-28#client-errors)
 */
@Serializable
internal data class GitHubErrorResponseDto(
    val message: String,
    val errors: List<GitHubErrorItemDto>? = null,
)
