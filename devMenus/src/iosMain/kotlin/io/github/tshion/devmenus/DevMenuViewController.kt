package io.github.tshion.devmenus

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController

public fun DevMenuViewController(): UIViewController = ComposeUIViewController {
    val provider = UIApplication.sharedApplication.delegate as? DevMenuProvider
    DevMenus(provider?.devMenuList)
}
