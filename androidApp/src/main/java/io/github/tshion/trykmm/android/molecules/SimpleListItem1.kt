package io.github.tshion.trykmm.android.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

/**
 * [android.R.layout.simple_list_item_1] のCustom View
 */
internal class SimpleListItem1View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    val text1: TextView

    init {
        View.inflate(context, android.R.layout.simple_list_item_1, this)
        text1 = findViewById(android.R.id.text1)
    }
}

/**
 * [android.R.layout.simple_list_item_1] のCompose
 *
 * ## 注意事項
 * * XML のテーマ設定を参照しているため、意図しない表示の場合はそちらを確認すること
 */
@Composable
internal fun SimpleListItem1(
    text: String,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth(),
        factory = { SimpleListItem1View(it) },
        update = { view ->
            view.text1.text = text
        },
        onReset = { view ->
            view.text1.text = null
        }
    )
}

@Composable
@Preview
@Preview(showBackground = true)
private fun Preview() {
    var onTappedText by remember { mutableStateOf("") }
    if (onTappedText.isNotBlank()) {
        AlertDialog(
            onDismissRequest = { onTappedText = "" },
            confirmButton = { },
            text = { Text(onTappedText) }
        )
    }

    val list = (0..<3).map {
        Triple(
            "Key$it",
            "List Text $it",
            "Tapped: $it",
        )
    }
    LazyColumn {
        items(
            items = list,
            key = { (key, _, _) -> key }
        ) { (_, listText, tappedText) ->
            SimpleListItem1(
                text = listText,
                modifier = Modifier
                    .clickable { onTappedText = tappedText }
            )
            HorizontalDivider()
        }
    }
}
