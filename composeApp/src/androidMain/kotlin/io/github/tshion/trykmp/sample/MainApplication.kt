package io.github.tshion.trykmp.sample

import android.app.Application
import io.github.tshion.devmenus.DevMenuAndroidViewer
import io.github.tshion.devmenus.DevMenuProvider
import io.github.tshion.devmenus.DevMenuSpec
import io.github.tshion.devmenus.DevMenuSpecItems
import io.github.tshion.trykmp.sample.templates.PurchaseConfirmationDialogFragment

internal class MainApplication : Application(), DevMenuProvider {

    override fun getDevMenuList(): List<DevMenuSpec> = listOf(
        DevMenuSpecItems.LocalPushGroup,
        DevMenuSpecItems.OsSettings,
        DevMenuSpec.Action("アプリ側のダイアログ表示") {
            val viewer = DevMenuAndroidViewer(it)
            PurchaseConfirmationDialogFragment().show(
                viewer.activity.supportFragmentManager,
                PurchaseConfirmationDialogFragment.TAG,
            )
        },
    )
}
