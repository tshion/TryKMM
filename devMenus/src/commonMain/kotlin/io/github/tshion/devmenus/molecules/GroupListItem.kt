package io.github.tshion.devmenus.molecules

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.tshion.devmenus.DevMenuSpec
import org.jetbrains.compose.resources.painterResource
import trykmm.devmenus.generated.resources.Res
import trykmm.devmenus.generated.resources.folder

/**
 * タップした際、関連するリストが表示されるリスト項目UI
 */
@Composable
internal fun GroupListItem(
    spec: DevMenuSpec.Group,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(spec.title)
        },
        modifier = Modifier.clickable {
            onClick()
        },
        leadingContent = {
            Icon(painterResource(Res.drawable.folder), null)
        }
    )
}

@Composable
@Preview
private fun Preview() {
    val spec = DevMenuSpec.Group(
        "Group Title",
    )
    GroupListItem(spec) {
    }
}
