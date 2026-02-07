package io.github.tshion.trykmp

import platform.Foundation.NSISO8601DateFormatter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class IOSTest {

    @Test
    fun testDateOffset() {
        val iso8601Text = "2012-10-05T11:20:31+09:00"

        val formatter = NSISO8601DateFormatter()
        val utc = formatter.dateFromString(iso8601Text)
        assertNotNull(utc)

        val utcText = formatter.stringFromDate(utc)
        println(utcText)
        assertNotEquals(iso8601Text, utcText)
    }

    @Test
    fun testDateUTC() {
        val iso8601Text = "2011-04-14T16:00:49Z"

        val formatter = NSISO8601DateFormatter()
        val utc = formatter.dateFromString(iso8601Text)
        assertNotNull(utc)

        val utcText = formatter.stringFromDate(utc)
        println(utcText)
        assertEquals(iso8601Text, utcText)
    }
}
