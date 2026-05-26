package io.github.tshion.devmenus.molecules

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.tshion.devmenus.DevMenuSpec

/**
 * タップした際、アクションが発動するリスト項目UI
 */
@Composable
internal fun ActionListItem(spec: DevMenuSpec.Action) {
    ListItem(
        headlineContent = {
            Text(spec.title)
        },
        modifier = Modifier.clickable {
            spec.action()
        },
        supportingContent = {
            if (!(spec.description.isNullOrBlank())) {
                Text(spec.description)
            }
        },
    )
}

@Composable
@Preview
private fun Preview() {
    val spec = DevMenuSpec.Action("Action Title") {
    }
    ActionListItem(spec)
}

@Composable
@Preview
private fun Preview2() {
    val spec = DevMenuSpec.Action(
        "Action Title",
        "description"
    ) {
    }
    ActionListItem(spec)
}
