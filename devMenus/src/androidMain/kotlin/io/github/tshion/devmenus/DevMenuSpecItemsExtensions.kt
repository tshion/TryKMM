package io.github.tshion.devmenus

import android.content.Intent
import android.provider.Settings

/**
 * OS 開発者オプション画面へ遷移するメニュー
 */
public val DevMenuSpecItems.OsDeveloperOptions: DevMenuSpec
    get() = DevMenuSpec.Action("OS 開発者オプション画面へ遷移") {
        val activity = DevMenuAndroidViewer(it).activity
        val intent = Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        }
    }
