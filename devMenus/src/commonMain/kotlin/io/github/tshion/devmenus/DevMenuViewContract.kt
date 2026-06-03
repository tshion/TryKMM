package io.github.tshion.devmenus

import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

/**
 * 開発者メニューが提供するUI 操作の定義
 */
public interface DevMenuViewContract {

    /**
     * Android Context の取得
     *
     * ※利用側で適宜Cast してください
     */
    @HiddenFromObjC
    @OptIn(ExperimentalObjCRefinement::class)
    @Suppress("PropertyName")
    public val _context: Any?


    /**
     * プログレスUI を非表示にする
     */
    public fun hideProgress()

    /**
     * プログレスUI の表示
     */
    public fun showProgress()

    /**
     * スナックバーの表示
     */
    @Suppress("SpellCheckingInspection")
    public fun showSnackbar(message: String)
}


internal class MockDevMenuViewer : DevMenuViewContract {
    override val _context: Any? = null

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun showSnackbar(message: String) {
    }
}
