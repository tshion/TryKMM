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
) {
    val viewModel = DevMenuSpecViewModel.create(specs)
    return ViewHost(viewModel)
}

@Composable
private fun ViewHost(
    specViewModel: DevMenuSpecViewModel,
    navViewModel: NavViewModel = viewModel { NavViewModel() },
) {
    navViewModel.navigateNext(Route.Index(""))
    NavDisplay(
        backStack = navViewModel.backStack,
        onBack = navViewModel::navigateBack,
        entryProvider = entryProvider {
            entry<Route.Index>(
                metadata = mapOf("keyHistory" to "valueHistory"),
            ) { key ->
                IndexPage(key.history, navViewModel, specViewModel)
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
