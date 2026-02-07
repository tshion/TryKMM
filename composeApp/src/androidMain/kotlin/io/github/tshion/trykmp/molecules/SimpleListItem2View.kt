package io.github.tshion.trykmp.molecules

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
 * [android.R.layout.simple_list_item_2] のCustom View
 */
internal class SimpleListItem2View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    val text1: TextView
    val text2: TextView

    init {
        View.inflate(context, android.R.layout.simple_list_item_2, this)
        text1 = findViewById(android.R.id.text1)
        text2 = findViewById(android.R.id.text2)
    }
}

/**
 * [android.R.layout.simple_list_item_2] のCompose
 *
 * ## 注意事項
 * * XML のテーマ設定を参照しているため、意図しない表示の場合はそちらを確認すること
 */
@Composable
internal fun SimpleListItem2(
    text1: String,
    text2: String,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth(),
        factory = { SimpleListItem2View(it) },
        update = { view ->
            view.text1.text = text1
            view.text2.text = text2
        },
        onReset = { view ->
            view.text1.text = null
            view.text2.text = null
        }
    )
}

@Composable
@Preview
@Preview(showBackground = true)
private fun Preview() {
    LazyColumn {
        items(3) { index ->
            SimpleListItem2(
                text1 = "Text $index",
                text2 = "position at $index",
            )
            HorizontalDivider()
        }
    }
}
