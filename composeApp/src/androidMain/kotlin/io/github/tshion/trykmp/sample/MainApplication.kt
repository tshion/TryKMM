package io.github.tshion.trykmp.sample

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import io.github.tshion.devmenus.DevMenuProvider
import io.github.tshion.devmenus.DevMenuSpec

internal class MainApplication : Application(), DevMenuProvider {

    @SuppressLint("MissingPermission", "QueryPermissionsNeeded")
    override val devMenuList = listOf(
        DevMenuSpec.Action("アプリのOS 設定画面へ遷移") {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = "package:${packageName}".toUri()
                addFlags(FLAG_ACTIVITY_NEW_TASK)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        },
        DevMenuSpec.Group(
            "ディープリンク",
            DevMenuSpec.Action("通常のアプリ起動") { viewer ->
                NotificationChannel(
                    CHANNEL_ID,
                    "Developer Menu",
                    NotificationManager.IMPORTANCE_DEFAULT,
                ).also {
                    val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                    manager.createNotificationChannel(it)
                }

                val intent = PendingIntent.getActivity(
                    this@MainApplication,
                    0,
                    Intent(this, MainActivity::class.java).apply {
                        flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                    },
                    PendingIntent.FLAG_IMMUTABLE,
                )
                val builder =
                    NotificationCompat.Builder(this@MainApplication, CHANNEL_ID)
                        .setContentTitle("ローカルプッシュ通知")
                        .setContentText("5秒後に表示される通知です")
                        .setSmallIcon(R.drawable.ic_menu_report_image)
                        .setContentIntent(intent)
                        .setAutoCancel(true)
                with(NotificationManagerCompat.from(this@MainApplication)) {
                    if (ActivityCompat.checkSelfPermission(
                            this@MainApplication,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            notify(0, builder.build())
                        }, 5000)
                        viewer.showSnackbar("ローカルプッシュ通知: 発行済み")
                    } else {
                        viewer.showSnackbar("通知権限を許可してください")
                    }
                }
            },
        ),
    )


    internal companion object {
        private const val CHANNEL_ID = "io.github.tshion.devmenus"
    }
}
