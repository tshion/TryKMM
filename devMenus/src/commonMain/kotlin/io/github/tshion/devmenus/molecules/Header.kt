package io.github.tshion.devmenus.molecules

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.tshion.devmenus.NavViewModel
import io.github.tshion.devmenus.Route
import org.jetbrains.compose.resources.painterResource
import trykmm.devmenus.generated.resources.Res
import trykmm.devmenus.generated.resources.arrow_back

/**
 * ヘッダー
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
internal fun Header(
    navViewModel: NavViewModel = viewModel(),
) {
    TopAppBar(
        title = {
            Text("開発者メニュー")
        },
        navigationIcon = {
            if (navViewModel.canGoBack()) {
                IconButton(onClick = navViewModel::navigateBack) {
                    Icon(painterResource(Res.drawable.arrow_back), null)
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}

@Composable
@Preview
private fun Preview(
    @PreviewParameter(HeaderPreviewProvider::class) navViewModel: NavViewModel,
) {
    Header(navViewModel)
}

private class HeaderPreviewProvider : PreviewParameterProvider<NavViewModel> {
    override val values = sequenceOf(
        NavViewModel(),
        NavViewModel().apply {
            navigateNext(Route.Index(""))
            navigateNext(Route.Index(""))
        },
    )
}
