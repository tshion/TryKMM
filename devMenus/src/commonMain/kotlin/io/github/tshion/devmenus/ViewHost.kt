package io.github.tshion.devmenus

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import io.github.tshion.devmenus.pages.IndexPage

internal data class RouteIndex(val history: String)

/**
 * 表示のエントリーポイント
 */
@Composable
internal fun ViewHost(
    viewModel: DevMenuSpecViewModel,
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
                IndexPage(history, viewModel) {
                    backStack.add(RouteIndex(it))
                }
            }
        },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
        },
    )
}
