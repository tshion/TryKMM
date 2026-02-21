package io.github.tshion.trykmp

import java.time.OffsetDateTime
import java.time.ZoneOffset
import kotlin.time.Instant

public typealias UtcDate = OffsetDateTime


internal fun Instant.toUtcDate(): UtcDate {
    val date = OffsetDateTime.parse(this.toString())
    val utc = date.withOffsetSameInstant(ZoneOffset.UTC)
    return utc
}
