package io.github.tshion.trykmmlib.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class GetLaunchesItemDto(
    val success: Boolean?,
    val flight_number: Int,
    val name: String,
    val date_utc: String,
    val date_local: String,
)
