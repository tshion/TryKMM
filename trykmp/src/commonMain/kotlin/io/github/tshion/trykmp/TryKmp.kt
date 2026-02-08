package io.github.tshion.trykmp

import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.ContinuationInterceptor
import kotlin.time.Clock

public class TryKmp internal constructor(
    private val httpClient: HttpClient,
) {

    public constructor() : this(HttpClient())


    private val gitHubWebApi by lazy {
        GitHubWebApi(httpClient)
    }

    private val platform = getPlatform()


    public suspend fun searchGitHubRepo(
        query: String,
    ): String {
        return gitHubWebApi.getSearchRepositories(query)
    }

    public fun timer(): Flow<String> = flow {
        val dispatcher = currentCoroutineContext()[ContinuationInterceptor]
        while (true) {
            val text = "${Clock.System.now()} (${platform.name} $dispatcher)"
            println(text)
            emit(text)
            delay(2_500)
        }
    }.flowOn(Dispatchers.Default)
}
