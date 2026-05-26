package io.github.tshion.devmenus.pages

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.tshion.devmenus.DevMenuSpec
import io.github.tshion.devmenus.DevMenuSpecViewModel
import io.github.tshion.devmenus.molecules.ActionListItem
import io.github.tshion.devmenus.molecules.GroupListItem

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun IndexPage(
    history: String,
    viewModel: DevMenuSpecViewModel,
    onNavigation: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("開発者メニュー")
                }
            )
        },
    ) { innerPadding ->
        val specs = viewModel.load(history)
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            itemsIndexed(specs) { index, spec ->
                when (spec) {
                    is DevMenuSpec.Action -> ActionListItem(spec)
                    is DevMenuSpec.Group -> GroupListItem(spec) {
                        val updated = viewModel.updateHistory(history, index, spec)
                        onNavigation(updated)
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
        DevMenuSpecViewModel.create(specs)
    ) {
    }
}
