package io.github.tshion.trykmp

import io.ktor.client.HttpClient
import kotlin.time.Clock

public class TryKmp internal constructor(
    private val httpClient: HttpClient,
) {

    public constructor() : this(HttpClient())


    private val gitHubWebApi by lazy {
        GitHubWebApi(httpClient)
    }

    internal val platform = getPlatform()


    public suspend fun searchGitHubRepo(
        query: String,
    ): String {
        return gitHubWebApi.getSearchRepositories(query)
    }

    public fun time(suffix: String = ""): String {
        return "${Clock.System.now()} (${platform.name}$suffix)"
    }
}
