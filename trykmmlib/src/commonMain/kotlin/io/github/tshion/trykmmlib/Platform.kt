package io.github.tshion.trykmmlib

internal interface Platform {
    val name: String
}

internal expect fun getPlatform(): Platform
