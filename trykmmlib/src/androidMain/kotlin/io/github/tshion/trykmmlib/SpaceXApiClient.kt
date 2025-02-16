package io.github.tshion.trykmmlib

import io.github.tshion.trykmmlib.entities.GetLaunchesResponse
import io.github.tshion.trykmmlib.entities.LaunchEntity
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

public class SpaceXApiClient internal constructor(
    client: OkHttpClient,
) {

    public constructor() : this(
        client = OkHttpClient.Builder().build(),
    )


    /** WebAPI エンドポイント */
    private val endpoint: SpaceXApiEndpoint

    init {
        endpoint = Retrofit.Builder()
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .baseUrl("https://api.spacexdata.com")
            .client(client)
            .build()
            .create(SpaceXApiEndpoint::class.java)
    }


    public suspend fun getLaunches(): GetLaunchesResponse {
        val response = endpoint.getLaunches()
        val result = GetLaunchesResponse(
            items = response.map { LaunchEntity(it) },
        )
        return result
    }
}
