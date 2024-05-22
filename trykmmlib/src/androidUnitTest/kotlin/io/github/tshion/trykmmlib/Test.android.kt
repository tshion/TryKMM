package io.github.tshion.trykmmlib

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", SampleModel().greet().contains("Android"))
    }
}
