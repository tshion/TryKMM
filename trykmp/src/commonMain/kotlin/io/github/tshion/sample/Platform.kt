package io.github.tshion.sample

public interface Platform {
    public val name: String
}

public expect fun getPlatform(): Platform