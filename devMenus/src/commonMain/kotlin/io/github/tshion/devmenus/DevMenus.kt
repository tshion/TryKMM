package io.github.tshion.devmenus

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import io.github.tshion.devmenus.pages.ListPage

public data object List

@Composable
internal fun DevMenus() {
    val backStack = remember { mutableStateListOf<Any>(List) }
    MaterialTheme {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is List -> NavEntry(key) {
                        ListPage()
                    }

                    else -> NavEntry(Unit) {
                        Text("Unknown route")
                    }
                }
            }
        )
    }
}
