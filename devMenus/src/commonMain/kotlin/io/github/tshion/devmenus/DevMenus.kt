package io.github.tshion.devmenus

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import io.github.tshion.devmenus.pages.ListPage

public data object ListPageState

@Composable
internal fun DevMenus(
    list: List<DevMenuEntity>?,
) {
    val backStack = remember { mutableStateListOf<Any>(ListPageState) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is ListPageState -> NavEntry(key) {
                    ListPage()
                }

                else -> NavEntry(Unit) {
                    Text("Unknown route")
                }
            }
        }
    )
}
