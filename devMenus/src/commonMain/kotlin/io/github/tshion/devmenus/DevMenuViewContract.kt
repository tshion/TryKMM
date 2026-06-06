package io.github.tshion.devmenus

public interface DevMenuBaseViewContract {
    /**
     * プログレスUI を非表示にする
     */
    public fun hideProgress()

    /**
     * ダイアログの表示
     */
    public fun showDialog(message: String)

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

/**
 * 開発者メニューが提供するUI 操作の定義
 */
public interface DevMenuViewContract : DevMenuBaseViewContract {

    /**
     * Compose の起点になっている画面
     *
     * ※利用側で適宜Cast してください
     */
    @Suppress("PropertyName")
    public val _host: Any?
}


internal class MockDevMenuViewer : DevMenuViewContract {
    override val _host: Any? = null

    override fun hideProgress() {
    }

    override fun showDialog(message: String) {
    }

    override fun showProgress() {
    }

    override fun showSnackbar(message: String) {
    }
}
