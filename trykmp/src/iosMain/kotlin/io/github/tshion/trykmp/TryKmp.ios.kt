import io.github.tshion.trykmp.TryKmpCommon
import io.github.tshion.trykmp.entities.GitHubRepo
import io.ktor.client.HttpClient
import platform.UIKit.UIDevice

public class TryKmp(
    httpClient: HttpClient = HttpClient(),
) : TryKmpCommon(httpClient) {

    override val name: String = UIDevice.currentDevice.let {
        "${it.systemName()} ${it.systemVersion}"
    }


    public suspend fun searchGitHubRepo(
        query: String,
    ): GitHubRepo {
        val result = gitHubWebApi.getSearchRepositories(query)
        return result.let { GitHubRepo(it) }
    }
}
