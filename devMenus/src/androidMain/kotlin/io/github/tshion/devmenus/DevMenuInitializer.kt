package io.github.tshion.devmenus

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.startup.Initializer

internal class DevMenuInitializer : Initializer<DevMenuInitializer> {

    override fun create(context: Context): DevMenuInitializer {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.EMPTY,
            context,
            DevMenuActivity::class.java,
        )
        ShortcutInfoCompat.Builder(context, "Developer Menu")
            .setLongLabel("開発者メニューの表示")
            .setShortLabel("開発者メニュー")
            .setIcon(IconCompat.createWithResource(context, android.R.drawable.ic_menu_search))
            .setIntent(intent)
            .build()
            .also { ShortcutManagerCompat.pushDynamicShortcut(context, it) }

        return this
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }
}
