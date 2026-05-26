package io.github.tshion.devmenus.pages

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.tshion.devmenus.DevMenuSpec
import io.github.tshion.devmenus.DevMenuSpecViewModel
import io.github.tshion.devmenus.NavViewModel
import io.github.tshion.devmenus.Route
import io.github.tshion.devmenus.molecules.ActionListItem
import io.github.tshion.devmenus.molecules.GroupListItem
import io.github.tshion.devmenus.molecules.Header

/**
 * 一覧画面
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun IndexPage(
    history: String,
    navViewModel: NavViewModel,
    specViewModel: DevMenuSpecViewModel,
) {
    Scaffold(
        topBar = { Header(navViewModel) },
    ) { innerPadding ->
        val specs = specViewModel.load(history)
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            itemsIndexed(specs) { index, spec ->
                when (spec) {
                    is DevMenuSpec.Action -> ActionListItem(spec)
                    is DevMenuSpec.Group -> GroupListItem(spec) {
                        val updated = specViewModel.updateHistory(history, index, spec)
                        navViewModel.navigateNext(Route.Index(updated))
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    val specs = listOf(
        DevMenuSpec.Group(
            "Group1 Title",
            DevMenuSpec.Group(
                "Group2 Title",
                DevMenuSpec.Action("Group2: Action Title") {
                },
            ),
            DevMenuSpec.Action("Group2: Action Title") {
            },
        ),
        DevMenuSpec.Action("Action1 Title") {
        },
    )
    IndexPage(
        "",
        viewModel(),
        DevMenuSpecViewModel.create(specs),
    )
}
