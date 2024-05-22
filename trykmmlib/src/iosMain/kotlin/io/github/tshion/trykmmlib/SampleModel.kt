package io.github.tshion.trykmmlib

import io.ktor.client.HttpClient

public class SampleModel : SampleModelBase(
    HttpClient(),
    getPlatform(),
)
