package io.github.tshion.devmenus

/**
 * 開発メニューで操作できるUI の定義
 */
public interface DevMenuViewContract {

    /**
     * Android Context の取得
     *
     * ※利用側で適宜Cast してください
     */
    public fun getContext(): Any?

    /**
     * スナックバーの表示
     */
    public fun showSnackbar(message: String)
}


internal class MockDevMenuViewer : DevMenuViewContract {
    override fun getContext(): Any? {
        return null
    }

    override fun showSnackbar(message: String) {
    }
}
