package io.github.tshion.trykmm.android.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
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
    LazyColumn {
        items(3) { index ->
            SimpleListItem1(
                text = "Text $index",
            )
            HorizontalDivider()
        }
    }
}
