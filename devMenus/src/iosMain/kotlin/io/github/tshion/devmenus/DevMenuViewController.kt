package io.github.tshion.devmenus

import androidx.compose.ui.window.ComposeUIViewController

/**
 * 開発者メニュー画面
 */
internal fun DevMenuViewController(specs: List<DevMenuSpec>?) = ComposeUIViewController {
    ViewHost(specs)
}
