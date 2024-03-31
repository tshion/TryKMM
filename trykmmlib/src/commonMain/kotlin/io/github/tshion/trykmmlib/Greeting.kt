package io.github.tshion.trykmmlib

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

public class Greeting {

    private val platform: Platform = getPlatform()
    private val rocketComponent = RocketComponent()


    /**
     * Get a greeting message.
     */
    public fun greet(): List<String> = buildList {
        add("Hello, ${platform.name}!")
        add(daysPhrase())
    }

    public fun greetStream(): Flow<String> = flow {
        emit("Hello, ${platform.name}!")
        delay(1.seconds)
        emit(daysPhrase())
        delay(1.seconds)
        emit(rocketComponent.launchPhrase())
    }

    public suspend fun launchPhrase(): String = rocketComponent.launchPhrase()
}
