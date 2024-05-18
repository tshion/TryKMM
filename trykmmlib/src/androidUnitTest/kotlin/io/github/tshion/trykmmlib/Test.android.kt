package io.github.tshion.trykmmlib

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greet().any { it.contains("Android") })
    }
}
