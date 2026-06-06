package io.github.tshion.devmenus

import platform.UIKit.UIViewController
import kotlin.experimental.ExperimentalObjCName

/**
 * (iOS 向け) 開発者メニューが提供するUI 操作
 */
@OptIn(ExperimentalObjCName::class)
public class DevMenuAppleViewer(
    @ObjCName("_") viewer: DevMenuViewContract,
) : DevMenuViewer(viewer) {

    public val viewController: UIViewController = viewer.getHost() as UIViewController
}
