package io.github.tshion.devmenus

import platform.UIKit.UIViewController

/**
 * UIViewController の取得
 */
public fun DevMenuViewContract.getViewController(): UIViewController {
    return _hostPage as UIViewController
}
