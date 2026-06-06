package io.github.tshion.devmenus

import platform.UIKit.UIViewController

/**
 * (iOS 向け) 開発者メニューが提供するUI 操作
 */
public class DevMenuAppleViewer(
    viewer: DevMenuViewContract,
) : DevMenuViewer(viewer) {

    public val viewController: UIViewController = viewer._host as UIViewController
}
