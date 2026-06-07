package io.github.tshion.devmenus

import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationShortcutIcon
import platform.UIKit.UIApplicationShortcutItem
import platform.UIKit.UINavigationController
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene
import kotlin.experimental.ExperimentalObjCName

/**
 * 開発者メニューの表示ロジック
 */
public class DevMenuPresenter private constructor(
    private val devMenuList: List<DevMenuSpec>,
) {

    /**
     * [UIApplicationShortcutItem] を取り扱えるかどうか
     */
    @OptIn(ExperimentalObjCName::class)
    public fun canHandle(
        @ObjCName("_") item: UIApplicationShortcutItem,
    ): Boolean = item.type == MENU_ID

    /**
     * 開発者メニューの起動
     */
    @OptIn(ExperimentalObjCName::class)
    public fun handle(
        @ObjCName("_") item: UIApplicationShortcutItem,
    ) {
        val viewController = DevMenuViewController(devMenuList)
        activeWindow?.rootViewController
            ?.let { findTopViewController(it) }
            ?.presentViewController(viewController, true, null)
    }


    public companion object {

        private const val MENU_ID = "io.github.tshion.devmenus.DevMenuPresenter"


        private val activeWindow: UIWindow?
            get() = UIApplication.sharedApplication.connectedScenes
                .firstOrNull { it is UIWindowScene }
                ?.let {
                    val scene = it as UIWindowScene
                    scene.windows
                }
                ?.firstOrNull { it is UIWindow && it.isKeyWindow() }
                ?.let { it as UIWindow }

        public lateinit var presenter: DevMenuPresenter
            private set


        private fun findTopViewController(viewController: UIViewController): UIViewController {
            val presentedViewController = viewController.presentedViewController
            if (presentedViewController != null) {
                return findTopViewController(presentedViewController)
            }

            val navigationController = viewController as? UINavigationController
            if (navigationController != null) {
                return findTopViewController(
                    navigationController.visibleViewController ?: navigationController
                )
            }

            val selectedViewController = (viewController as? UITabBarController)
                ?.selectedViewController
            if (selectedViewController != null) {
                return findTopViewController(selectedViewController)
            }

            return viewController
        }

        /**
         * 開発者メニュー用の [UIApplicationShortcutItem] のセットアップ
         */
        @OptIn(ExperimentalObjCName::class)
        public fun setupShortcutItem(
            @ObjCName("_") devMenuList: List<DevMenuSpec>?,
        ): UIApplicationShortcutItem {
            presenter = DevMenuPresenter(devMenuList ?: emptyList())
            return UIApplicationShortcutItem(
                type = MENU_ID,
                localizedTitle = "開発者メニューの表示",
                localizedSubtitle = null,
                icon = UIApplicationShortcutIcon.iconWithSystemImageName(
                    systemImageName = "magnifyingglass",
                ),
                userInfo = null,
            )
        }
    }
}
