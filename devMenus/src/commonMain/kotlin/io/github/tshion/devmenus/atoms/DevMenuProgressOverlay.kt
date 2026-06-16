package io.github.tshion.devmenus.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

/**
 * 読み込み表示
 *
 * ## 備考
 * * このUI の下にあるUI をタップできないようにイベント伝搬をここで止めている
 */
@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
internal fun DevMenuProgressOverlay() {
    Box(
        modifier = Modifier
            .background(Color(0x52000000))
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, _ -> change.consume() }
            },
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
