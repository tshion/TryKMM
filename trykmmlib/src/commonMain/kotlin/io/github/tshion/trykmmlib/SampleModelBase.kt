package io.github.tshion.trykmmlib

import io.github.tshion.trykmmlib.entities.RocketLaunchEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

/**
 * Model のサンプル実装
 */
public abstract class SampleModelBase internal constructor(
    httpClient: HttpClient,
    private val platform: Platform,
) {

    private val _httpClient by lazy {
        httpClient.config {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }


    public fun daysUntilNewYear(): Int {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val closestNewYear = LocalDate(today.year + 1, 1, 1)
        return today.daysUntil(closestNewYear)
    }

    /**
     * このインスタンスを破棄する際の処理
     */
    public fun deinit() {
        _httpClient.close()
    }

    private suspend fun getDateOfLastSuccessfulLaunch(): String {
        val rockets = _httpClient.get("https://api.spacexdata.com/v4/launches")
            .body<List<RocketLaunchEntity>>()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        val date = Instant.parse(lastSuccessLaunch.launchDateUTC)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        return "${date.month} ${date.dayOfMonth}, ${date.year}"
    }

    public fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    public suspend fun launchPhrase(): String = try {
        "The last successful launch was on ${getDateOfLastSuccessfulLaunch()} 🚀"
    } catch (e: Exception) {
        println("Exception during getting the date of the last successful launch $e")
        "Error occurred"
    }

    public fun sampleStream(): Flow<String> = flow {
        emit("Hello, ${platform.name}!")
        delay(1.seconds)
        emit("There are only ${daysUntilNewYear()} days left until New Year! 🎆")
        delay(1.seconds)
        emit(launchPhrase())
    }
}
