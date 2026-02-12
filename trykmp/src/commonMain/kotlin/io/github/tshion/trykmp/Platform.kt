package io.github.tshion.trykmp

internal interface Platform {
    val name: String
}

internal expect fun getPlatform(): Platform
