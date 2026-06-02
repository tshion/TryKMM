package io.github.tshion.devmenus

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun getContext(): Any? {
    return LocalContext.current
}
