package io.github.tshion.devmenus

/**
 * 遷移パスの定義
 */
internal sealed class Route {

    /**
     * 一覧画面
     */
    data class Index(
        val history: String,
    ) : Route()
}
