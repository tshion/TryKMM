package io.github.tshion.trykmmlib

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greet().any { it.contains("Hello") }, "Check 'Hello' is mentioned")
    }
}
