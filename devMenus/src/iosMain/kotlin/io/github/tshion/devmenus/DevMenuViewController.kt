package io.github.tshion.devmenus

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController

/**
 * 開発メニュー画面
 */
public fun DevMenuViewController(): UIViewController = ComposeUIViewController {
    val provider = UIApplication.sharedApplication.delegate as? DevMenuProvider
    val viewModel = DevMenuSpecViewModel.create(
        specs = provider?.devMenuList,
    )
    ViewHost(viewModel)
}
