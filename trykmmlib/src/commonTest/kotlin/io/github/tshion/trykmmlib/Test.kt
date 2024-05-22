package io.github.tshion.trykmmlib

import io.ktor.client.HttpClient
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        val model = object : SampleModelBase(
            HttpClient(),
            object : Platform {
                override val name = "multiplatform"
            }
        ) {
        }
        assertTrue(model.greet().contains("Hello"), "Check 'Hello' is mentioned")
    }
}
