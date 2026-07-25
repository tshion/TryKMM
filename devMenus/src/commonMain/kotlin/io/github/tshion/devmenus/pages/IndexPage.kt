package io.github.tshion.devmenus.pages

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.tshion.devmenus.DevMenuSpec
import io.github.tshion.devmenus.DevMenuSpecViewModel
import io.github.tshion.devmenus.DevMenuViewContract
import io.github.tshion.devmenus.NavViewModel
import io.github.tshion.devmenus.Route
import io.github.tshion.devmenus.atoms.DevMenuProgressOverlay
import io.github.tshion.devmenus.getHostPage
import io.github.tshion.devmenus.molecules.ActionListItem
import io.github.tshion.devmenus.molecules.GroupListItem
import io.github.tshion.devmenus.molecules.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * 一覧画面
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun IndexPage(
    history: String,
    navSharedViewModel: NavViewModel,
    specSharedViewModel: DevMenuSpecViewModel,
) {
    var dialogMessage by remember { mutableStateOf("") }
    var isShowProgress by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val hostPage = getHostPage()
    val viewer = object : DevMenuViewContract {
        override fun getHost(): Any {
            return hostPage
        }

        override fun dismissSnackbar() {
            snackbarScope.cancel()
            snackbarScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
            snackbarHostState.currentSnackbarData?.dismiss()
        }

        override fun hideProgress() {
            isShowProgress = false
        }

        override fun showDialog(message: String) {
            dialogMessage = message
        }

        override fun showProgress() {
            isShowProgress = true
        }

        override fun showSnackbar(message: String) {
            scope.launch {
                snackbarScope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }
        }
    }

    Scaffold(
        topBar = { Header() },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        val specs = specSharedViewModel.load(history)
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            itemsIndexed(specs) { index, spec ->
                when (spec) {
                    is DevMenuSpec.Action -> ActionListItem(spec, viewer)
                    is DevMenuSpec.Group -> GroupListItem(spec) {
                        val updated = specSharedViewModel.updateHistory(history, index, spec)
                        navSharedViewModel.navigateNext(Route.Index(updated))
                    }
                }
                HorizontalDivider()
            }
        }
        if (isShowProgress) {
            DevMenuProgressOverlay()
        }
        if (dialogMessage.isNotBlank()) {
            AlertDialog(
                onDismissRequest = {
                    dialogMessage = ""
                },
                confirmButton = {
                },
                text = {
                    Text(
                        text = dialogMessage,
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                    )
                },
            )
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
        navSharedViewModel = viewModel { NavViewModel() },
        specSharedViewModel = DevMenuSpecViewModel.create(specs),
    )
}
