package io.github.tshion.trykmp

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.ktor.client.HttpClient
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.Ignore
import kotlin.test.assertTrue

/**
 * [GitHubWebApi] を試すためのプレイグラウンド
 */
@RunWith(AndroidJUnit4::class)
class GitHubWebApiPlayground {

    @Ignore("実環境へ通信するため")
    @Test
    fun play_getSearchRepositories() = runTest {
        val query = "android"

        val api = GitHubWebApi(HttpClient())
        val result = api.getSearchRepositories(query)
        assertTrue(result.items.isNotEmpty())
    }
}
