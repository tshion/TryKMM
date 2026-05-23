package io.github.tshion.devmenus.molecules

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.tshion.devmenus.DevMenuEntity
import org.jetbrains.compose.resources.painterResource
import trykmm.devmenus.generated.resources.Res
import trykmm.devmenus.generated.resources.folder

/**
 * タップした際、関連するリストが表示されるリスト項目UI
 */
@Composable
internal fun GroupListItem(spec: DevMenuEntity) {
    ListItem(
        headlineContent = {
            Text(spec.title)
        },
        modifier = Modifier.clickable {
            // TODO: 画面遷移
        },
        leadingContent = {
            Icon(painterResource(Res.drawable.folder), null)
        }
    )
}

@Composable
@Preview
private fun Preview() {
    val spec = DevMenuEntity.newGroup(
        "Group Title",
    )
    GroupListItem(spec)
}
