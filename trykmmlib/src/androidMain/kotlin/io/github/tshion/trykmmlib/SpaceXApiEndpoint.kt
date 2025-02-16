package io.github.tshion.trykmmlib

import io.github.tshion.trykmmlib.dto.GetLaunchesItemDto
import retrofit2.http.GET

/**
 * [SpaceX API](https://github.com/r-spacex/SpaceX-API) のエンドポイント
 */
internal interface SpaceXApiEndpoint {

    /**
     * [Get all launches](https://github.com/r-spacex/SpaceX-API/blob/master/docs/launches/v4/all.md)
     */
    @GET("v4/launches")
    suspend fun getLaunches(): List<GetLaunchesItemDto>
}
