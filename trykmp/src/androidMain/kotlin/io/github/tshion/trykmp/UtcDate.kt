package io.github.tshion.trykmp

import java.time.OffsetDateTime
import java.time.ZoneOffset

public typealias UtcDate = OffsetDateTime


internal fun String.toUtcDate(): UtcDate {
    val date = OffsetDateTime.parse(this)
    val utc = date.withOffsetSameInstant(ZoneOffset.UTC)
    return utc
}
