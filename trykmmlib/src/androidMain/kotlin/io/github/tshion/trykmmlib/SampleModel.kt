package io.github.tshion.trykmmlib

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

public class SampleModel(
    httpClient: HttpClient = HttpClient(OkHttp),
) : SampleModelBase(httpClient, getPlatform())
