package io.github.tshion.trykmmlib

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(SampleModel().greet().contains("iOS"), "Check iOS is mentioned")
    }
}
