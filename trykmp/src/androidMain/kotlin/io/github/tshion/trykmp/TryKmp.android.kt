package io.github.tshion.trykmp

import io.github.tshion.trykmp.entities.GitHubRepo
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.ContinuationInterceptor

public class TryKmp internal constructor(
    httpClient: HttpClient,
) : TryKmpCommon(httpClient) {

    public constructor() : this(HttpClient())


    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"


    public suspend fun searchGitHubRepo(
        query: String,
    ): GitHubRepo {
        val result = gitHubWebApi.getSearchRepositories(query)
        return GitHubRepo(result)
    }

    public fun timer(): Flow<String> = flow {
        val dispatcher = currentCoroutineContext()[ContinuationInterceptor]
        while (true) {
            val text = time(suffix = " $dispatcher")
            println(text)
            emit(text)
            delay(2_500)
        }
    }.flowOn(Dispatchers.Default)
}
