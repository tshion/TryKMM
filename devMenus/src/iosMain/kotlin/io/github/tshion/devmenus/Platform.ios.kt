package io.github.tshion.devmenus

import androidx.compose.runtime.Composable
import androidx.compose.ui.uikit.LocalUIViewController

@Composable
internal actual fun getHostPage(): Any {
    return LocalUIViewController.current
}
