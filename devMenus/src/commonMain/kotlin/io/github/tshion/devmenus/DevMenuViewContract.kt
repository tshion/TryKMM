package io.github.tshion.devmenus

import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

/**
 * 開発メニューが提供するUI 操作の定義
 */
public interface DevMenuViewContract {

    /**
     * Android Context の取得
     *
     * ※利用側で適宜Cast してください
     */
    @HiddenFromObjC
    @OptIn(ExperimentalObjCRefinement::class)
    public val _context: Any?

    /**
     * スナックバーの表示
     */
    public fun showSnackbar(message: String)
}


internal class MockDevMenuViewer : DevMenuViewContract {
    override val _context: Any? = null

    override fun showSnackbar(message: String) {
    }
}
