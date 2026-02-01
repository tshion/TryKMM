package io.github.tshion.sample

import android.os.Build

internal class AndroidPlatform : Platform {
    override val name: String = "${Build.MODEL}(Android ${Build.VERSION.SDK_INT})"
}

internal actual fun getPlatform(): Platform = AndroidPlatform()
