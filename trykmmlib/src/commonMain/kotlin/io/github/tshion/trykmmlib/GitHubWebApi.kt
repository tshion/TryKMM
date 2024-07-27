package io.github.tshion.trykmmlib

import io.github.tshion.trykmmlib.webdto.GetSearchRepositoriesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * GitHub REST API への接続
 */
public class GitHubWebApi internal constructor(
//    applicationId: String,
    private val baseUrl: String,
//    cacheDir: File,
    client: HttpClient,
) {

    /**
     * @param client アプリ全体で共有しているOkHttpClient
     */
    public constructor(
//        applicationId: String,
//        cacheDir: File,
        client: HttpClient,
    ) : this("https://api.github.com", client)


    private val _httpClient by lazy {
        client.config {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpCache)
        }
    }


    /**
     * このインスタンスを破棄する際の処理
     */
    public fun deinit() {
        _httpClient.close()
    }

    /**
     * Search repositories
     *
     * Find repositories via various criteria.
     * This method returns up to 100 results [per page](https://docs.github.com/rest/overview/resources-in-the-rest-api#pagination).
     *
     * When searching for repositories, you can get text match metadata for the **name** and **description** fields when you pass the &#x60;text-match&#x60; media type.
     *
     * For more details about how to receive highlighted search results, see [Text match metadata](https://docs.github.com/rest/search/search#text-match-metadata).
     *
     * For example, if you want to search for popular Tetris repositories written in assembly code, your query might look like this:  &#x60;q&#x3D;tetris+language:assembly&amp;sort&#x3D;stars&amp;order&#x3D;desc&#x60;  This query searches for repositories with the word &#x60;tetris&#x60; in the name, the description, or the README.
     * The results are limited to repositories where the primary language is assembly.
     * The results are sorted by stars in descending order, so that the most popular repositories appear first in the search results.
     *
     * @param q The query contains one or more search keywords and qualifiers. Qualifiers allow you to limit your search to specific areas of GitHub. The REST API supports the same qualifiers as the web interface for GitHub. To learn more about the format of the query, see [Constructing a search query](https://docs.github.com/rest/search/search#constructing-a-search-query). See \&quot;[Searching for repositories](https://docs.github.com/articles/searching-for-repositories/)\&quot; for a detailed list of qualifiers.
     * @param sort Sorts the results of your query by number of &#x60;stars&#x60;, &#x60;forks&#x60;, or &#x60;help-wanted-issues&#x60; or how recently the items were &#x60;updated&#x60;. Default: [best match](https://docs.github.com/rest/search/search#ranking-search-results) (optional)
     * @param order Determines whether the first search result returned is the highest number of matches (&#x60;desc&#x60;) or lowest number of matches (&#x60;asc&#x60;). This parameter is ignored unless you provide &#x60;sort&#x60;. (optional, default to desc)
     * @param perPage The number of results per page (max 100). (optional, default to 30)
     * @param page Page number of the results to fetch. (optional, default to 1)
     */
    public suspend fun getSearchRepositories(
        q: String,
        sort: String? = null,
        order: String? = null,
        perPage: Int? = null,
        page: Int? = null,
    ): String {
        val response: GetSearchRepositoriesResponseDto = _httpClient.get(baseUrl) {
            url {
                appendPathSegments("search/repositories")

                parameters.append("q", q)
                sort?.also { parameters.append("sort", it) }
                order?.also { parameters.append("order", it) }
                perPage?.also { parameters.append("perPage", it.toString()) }
                page?.also { parameters.append("page", it.toString()) }
            }
        }.body()
        return response.toString()
    }
}
