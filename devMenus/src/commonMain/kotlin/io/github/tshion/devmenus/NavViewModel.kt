package io.github.tshion.devmenus

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

/**
 * ナビゲーションを管理するViewModel
 */
internal class NavViewModel : ViewModel() {

    val backStack = SnapshotStateList<Route>()


    /**
     * 一つ前の画面に戻れるかどうか
     */
    fun canGoBack(): Boolean {
        return 1 < backStack.size
    }

    /**
     * 履歴を一つ戻る
     */
    fun navigateBack() {
        backStack.removeLastOrNull()
    }

    /**
     * 次の画面へ遷移する
     */
    fun navigateNext(route: Route) {
        backStack.add(route)
    }
}
