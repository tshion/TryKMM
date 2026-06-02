package io.github.tshion.devmenus

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import kotlin.experimental.ExperimentalObjCName

/**
 * 開発者メニュー画面
 */
@OptIn(ExperimentalObjCName::class)
public fun DevMenuViewController(
    @ObjCName("_") specs: List<DevMenuSpec>?,
): UIViewController = ComposeUIViewController {
    ViewHost(specs)
}
