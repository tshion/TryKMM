package io.github.tshion.sample

import platform.UIKit.UIDevice

internal class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.let {
        "${it.model}(${it.systemName} ${it.systemVersion})"
    }
}

internal actual fun getPlatform(): Platform = IOSPlatform()
