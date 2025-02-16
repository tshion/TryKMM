package io.github.tshion.trykmmlib.entities

import io.github.tshion.trykmmlib.UtcDate
import io.github.tshion.trykmmlib.dto.GetLaunchesItemDto
import io.github.tshion.trykmmlib.toUtcDate

public data class LaunchEntity(
    val flightNumber: Int,
    val missionName: String,
    val launchDate: UtcDate,
    val launchSuccess: Boolean?,
) {

    internal constructor(dto: GetLaunchesItemDto) : this(
        flightNumber = dto.flight_number,
        missionName = dto.name,
        launchDate = dto.date_local.toUtcDate(),
        launchSuccess = dto.success,
    ) {
//        val local = dto.date_local.toUtcDate()
//        val utc = dto.date_utc.toUtcDate()
//        println(local == utc)
    }
}
