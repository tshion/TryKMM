package io.github.tshion.devmenus

/**
 * 開発メニューで操作できるUI の定義
 */
public interface DevMenuViewer {

    /**
     * スナックバーの表示
     */
    public fun showSnackbar(message: String)
}


internal class MockDevMenuViewer : DevMenuViewer {
    override fun showSnackbar(message: String) {
    }
}
