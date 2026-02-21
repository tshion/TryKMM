package io.github.tshion.trykmp

import io.ktor.client.HttpClient
import kotlin.time.Clock

public abstract class TryKmpCommon internal constructor(
    private val httpClient: HttpClient,
) {

    internal val gitHubWebApi by lazy {
        GitHubWebApi(httpClient)
    }

    protected abstract val name: String


    public fun time(suffix: String = ""): String {
        return "${Clock.System.now()} (${name}$suffix)"
    }
}
