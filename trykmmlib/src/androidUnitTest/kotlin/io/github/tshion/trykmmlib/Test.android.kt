package io.github.tshion.trykmmlib

import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", SampleModel().greet().contains("Android"))
    }

    @Test
    fun testDateOffset() {
        val iso8601Text = "2012-10-05T11:20:31+09:00"

        val date = OffsetDateTime.parse(iso8601Text)
        val utc = date.withOffsetSameInstant(ZoneOffset.UTC)

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateText = date.format(pattern)
        val utcText = utc.format(pattern)
        assertNotEquals(dateText, utcText)
    }

    @Test
    fun testDateUTC() {
        val iso8601Text = "2011-04-14T16:00:49Z"

        val date = OffsetDateTime.parse(iso8601Text)
        val utc = date.withOffsetSameInstant(ZoneOffset.UTC)

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateText = date.format(pattern)
        val utcText = utc.format(pattern)
        assertEquals(dateText, utcText)
    }
}
