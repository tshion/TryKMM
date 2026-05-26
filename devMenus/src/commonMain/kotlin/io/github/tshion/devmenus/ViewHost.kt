package io.github.tshion.devmenus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

internal data class RouteIndex(val history: String)

/**
 * 表示のエントリーポイント
 */
@Composable
internal fun ViewHost(
    viewModel: DevMenuSpecViewModel = DevMenuSpecViewModel.create(emptyList()),
) {
    val backStack = remember { mutableStateListOf<Any>(RouteIndex("")) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<RouteIndex>(
                metadata = mapOf("keyHistory" to "valueHistory"),
            ) { key ->
                val history = key.history
            }
        }
    )
}
