package io.github.tshion.devmenus

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

public fun DevMenuViewController(): UIViewController = ComposeUIViewController {
    App()
}
