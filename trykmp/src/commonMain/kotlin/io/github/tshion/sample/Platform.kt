package io.github.tshion.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform