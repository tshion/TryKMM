package io.github.tshion.devmenus

/**
 * 開発者メニューが提供するUI 操作
 */
public open class DevMenuViewer(
    protected val viewer: DevMenuViewContract,
) : DevMenuBaseViewContract {

    override fun dismissSnackbar() {
        viewer.dismissSnackbar()
    }

    override fun hideProgress() {
        viewer.hideProgress()
    }

    override fun showDialog(message: String) {
        viewer.showDialog(message)
    }

    override fun showProgress() {
        viewer.showProgress()
    }

    override fun showSnackbar(message: String) {
        viewer.showSnackbar(message)
    }
}
