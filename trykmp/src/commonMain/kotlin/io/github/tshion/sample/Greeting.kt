package io.github.tshion.sample

public class Greeting {
    private val platform = getPlatform()

    public fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}