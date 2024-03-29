package io.github.tshion.trykmmlib

internal class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

internal actual fun getPlatform(): Platform = AndroidPlatform()
