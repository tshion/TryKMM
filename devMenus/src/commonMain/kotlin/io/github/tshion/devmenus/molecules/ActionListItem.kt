package io.github.tshion.devmenus.molecules

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * タップした際、アクションが発動するリスト項目UI
 */
@Composable
internal fun ActionListItem(
    text: String,
) {
    ListItem(
        headlineContent = {
            Text(text)
        },
    )
}

@Composable
@Preview
private fun Preview() {
    ActionListItem("Test")
}
