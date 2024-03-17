package io.github.tshion.trykmmlib

public class Greeting {

    private val platform: Platform = getPlatform()


    /**
     * Get a greeting message.
     */
    public fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
