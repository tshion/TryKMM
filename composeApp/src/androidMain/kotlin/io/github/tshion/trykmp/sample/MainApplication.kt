package io.github.tshion.trykmp.sample

import android.Manifest.permission.POST_NOTIFICATIONS
import android.R
import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import io.github.tshion.devmenus.DevMenuAndroidViewer
import io.github.tshion.devmenus.DevMenuProvider
import io.github.tshion.devmenus.DevMenuSpec
import io.github.tshion.trykmp.sample.templates.PurchaseConfirmationDialogFragment

internal class MainApplication : Application(), DevMenuProvider {

    @SuppressLint("MissingPermission", "QueryPermissionsNeeded")
    override val devMenuList = listOf(
        DevMenuSpec.Group(
            "ローカルプッシュ通知",
            DevMenuSpec.Action("通知設定へ遷移") {
                val viewer = DevMenuAndroidViewer(it)
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                }
                if (intent.resolveActivity(packageManager) != null) {
                    viewer.activity.startActivity(intent)
                }
            },
            DevMenuSpec.Action("アプリを起動する通知") {
                val viewer = DevMenuAndroidViewer(it)
                NotificationChannel(
                    CHANNEL_ID,
                    "Developer Menu",
                    NotificationManager.IMPORTANCE_DEFAULT,
                ).also {
                    val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    manager.createNotificationChannel(it)
                }

                val appContext = this@MainApplication
                val intent = PendingIntent.getActivity(
                    appContext,
                    0,
                    Intent(this, MainActivity::class.java).apply {
                        flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                    },
                    PendingIntent.FLAG_IMMUTABLE,
                )
                val builder = NotificationCompat.Builder(appContext, CHANNEL_ID)
                    .setContentTitle("ローカルプッシュ通知")
                    .setContentText("タップした際、アプリを起動する")
                    .setSmallIcon(R.drawable.ic_menu_report_image)
                    .setContentIntent(intent)
                    .setAutoCancel(true)
                if (ActivityCompat.checkSelfPermission(
                        appContext,
                        POST_NOTIFICATIONS
                    ) == PERMISSION_GRANTED
                ) {
                    with(NotificationManagerCompat.from(appContext)) {
                        notify(0, builder.build())
                    }
                    viewer.showSnackbar("ローカルプッシュ通知: 発行済み")
                } else {
                    viewer.showSnackbar("通知権限を許可してください")
                }
            },
        ),
        DevMenuSpec.Action("アプリのOS 設定画面へ遷移") {
            val viewer = DevMenuAndroidViewer(it)
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = "package:${packageName}".toUri()
            }
            if (intent.resolveActivity(packageManager) != null) {
                viewer.activity.startActivity(intent)
            }
        },
        DevMenuSpec.Action("アプリ側のダイアログ表示") {
            val viewer = DevMenuAndroidViewer(it)
            PurchaseConfirmationDialogFragment().show(
                viewer.activity.supportFragmentManager,
                PurchaseConfirmationDialogFragment.TAG,
            )
        },
    )


    internal companion object {
        private const val CHANNEL_ID = "io.github.tshion.devmenus"
    }
}
