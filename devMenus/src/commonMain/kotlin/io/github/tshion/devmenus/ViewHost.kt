package io.github.tshion.devmenus

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import io.github.tshion.devmenus.pages.IndexPage

/**
 * 表示のエントリーポイント
 */
@Composable
internal fun ViewHost(
    specs: List<DevMenuSpec>?,
    navSharedViewModel: NavViewModel = viewModel { NavViewModel() },
) {
    // NOTE: ここでインスタンス作成することでViewModel を使いまわせるようにしている
    @Suppress("unused", "UnusedVariable")
    val specSharedViewModel = DevMenuSpecViewModel.create(specs)

    navSharedViewModel.navigateNext(Route.Index(""))
    NavDisplay(
        entryDecorators = emptyList(
            // NOTE:
            // 未設定にすることでNavEntry 毎にViewModelStoreOwner が設定されることを防ぎ、
            // ViewModel を使いまわせるようにしている
        ),
        backStack = navSharedViewModel.backStack,
        onBack = navSharedViewModel::navigateBack,
        entryProvider = entryProvider {
            entry<Route.Index>(
                metadata = mapOf("keyHistory" to "valueHistory"),
            ) { key ->
                IndexPage(key.history)
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
