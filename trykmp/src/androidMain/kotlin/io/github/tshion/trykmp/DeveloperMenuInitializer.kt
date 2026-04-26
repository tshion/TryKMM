package io.github.tshion.trykmp

import android.content.Context
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.startup.Initializer

public class DeveloperMenuInitializer : Initializer<DeveloperMenuInitializer> {
    override fun create(context: Context): DeveloperMenuInitializer {
        ShortcutInfoCompat.Builder(context, "Developer Menu")
            .setShortLabel("Developer Menu")
            .setLongLabel("Open Developer Menu")
            .setIcon(IconCompat.createWithResource(context, android.R.drawable.ic_menu_search))
            .setIntent(DeveloperMenuActivity.createIntent(context))
            .build()
            .also { ShortcutManagerCompat.pushDynamicShortcut(context, it) }

        return this
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }
}
