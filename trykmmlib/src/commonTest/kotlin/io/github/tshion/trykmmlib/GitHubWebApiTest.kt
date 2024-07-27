package io.github.tshion.trykmmlib

import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue

class GitHubWebApiTest {

    @Ignore
    @Test
    fun play_getSearchRepositories() {
        val api = GitHubWebApi(HttpClient())
        runBlocking { // FIXME
            val result = api.getSearchRepositories("android")
            assertTrue(result.isNotBlank())
        }
    }
}
