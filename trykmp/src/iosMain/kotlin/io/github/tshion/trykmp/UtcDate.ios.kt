package io.github.tshion.trykmp

import platform.Foundation.NSDate
import platform.Foundation.NSISO8601DateFormatter
import kotlin.time.Instant

public typealias UtcDate = NSDate


private val formatter = NSISO8601DateFormatter()


internal fun Instant.toUtcDate() = toString().let {
    formatter.dateFromString(it)
}
