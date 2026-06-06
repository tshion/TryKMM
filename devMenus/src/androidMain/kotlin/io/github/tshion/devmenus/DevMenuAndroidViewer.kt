package io.github.tshion.devmenus

import androidx.fragment.app.FragmentActivity

/**
 * (Android 向け) 開発者メニューが提供するUI 操作
 */
public class DevMenuAndroidViewer(
    viewer: DevMenuViewContract,
) : DevMenuViewer(viewer) {

    public val activity: FragmentActivity = viewer._host as FragmentActivity
}
