package io.github.tshion.devmenus

public interface DevMenuBaseViewContract {

    @Suppress("SpellCheckingInspection")
    public fun dismissSnackbar()

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
    public fun getHost(): Any
}


internal class MockDevMenuViewer : DevMenuViewContract {
    override fun getHost(): Any {
        return Unit
    }

    override fun dismissSnackbar() {
    }

    override fun hideProgress() {
    }

    override fun showDialog(message: String) {
    }

    override fun showProgress() {
    }

    override fun showSnackbar(message: String) {
    }
}
