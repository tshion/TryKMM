package io.github.tshion.devmenus.molecules

import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import trykmm.devmenus.generated.resources.Res
import trykmm.devmenus.generated.resources.folder

@Composable
internal fun GroupListItem(
    text: String,
) {
    ListItem(
        headlineContent = {
            Text(text)
        },
        leadingContent = {
            Icon(painterResource(Res.drawable.folder), null)
        }
    )
}

@Composable
@Preview
private fun Preview() {
    GroupListItem("Test")
}
