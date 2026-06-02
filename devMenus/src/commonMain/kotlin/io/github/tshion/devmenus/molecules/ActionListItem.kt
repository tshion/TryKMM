package io.github.tshion.devmenus.molecules

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.github.tshion.devmenus.DevMenuSpec
import io.github.tshion.devmenus.DevMenuViewContract
import io.github.tshion.devmenus.MockDevMenuViewer

/**
 * タップした際、アクションが発動するリスト項目UI
 */
@Composable
internal fun ActionListItem(
    spec: DevMenuSpec.Action,
    viewer: DevMenuViewContract,
) {
    ListItem(
        headlineContent = {
            Text(spec.title)
        },
        modifier = Modifier.clickable {
            spec.action(viewer)
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
private fun Preview(
    @PreviewParameter(ActionListItemPreviewProvider::class) text: String?,
) {
    val spec = DevMenuSpec.Action("Action Title", text) {
    }
    ActionListItem(spec, MockDevMenuViewer())
}

private class ActionListItemPreviewProvider : PreviewParameterProvider<String?> {
    override val values = sequenceOf(
        null,
        "",
        "description",
    )
}
