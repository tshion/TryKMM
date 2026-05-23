package io.github.tshion.devmenus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import io.github.tshion.devmenus.pages.ListPage

public data object ListPageKey

@Composable
internal fun DevMenus(
    list: List<DevMenuEntity>?,
) {

    val backStack = remember { mutableStateListOf<Any>(ListPageKey) }
    backStack.add(ListPage())

    // FIXME: iOS 対応されたらNavDisplay ベースに書き換える
//    NavDisplay(
//        backStack = backStack,
//        onBack = { backStack.removeLastOrNull() },
//        entryProvider = { key ->
//            when (key) {
//                is List -> NavEntry(key) {
//                    ListPage()
//                }
//
//                else -> NavEntry(Unit) {
//                    Text("Unknown route")
//                }
//            }
//        }
//    )
}
