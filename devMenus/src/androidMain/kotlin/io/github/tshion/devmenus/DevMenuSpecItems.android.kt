package io.github.tshion.devmenus

import android.Manifest.permission.POST_NOTIFICATIONS
import android.R
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri

public actual object DevMenuSpecItems {

    @SuppressLint("MissingPermission")
    public actual fun goLocalPushGroup(): DevMenuSpec = DevMenuSpec.Group(
        "ローカルプッシュ通知",
        DevMenuSpec.Action("通知設定へ遷移") {
            val activity = DevMenuAndroidViewer(it).activity
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, activity.packageName)
            }
            if (intent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(intent)
            }
        },
        DevMenuSpec.Action("アプリを起動する通知") {
            val viewer = DevMenuAndroidViewer(it)
            val activity = viewer.activity
            val packageName = activity.packageName

            NotificationChannel(
                packageName,
                "Developer Menu",
                NotificationManager.IMPORTANCE_DEFAULT,
            ).also {
                val manager = activity.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(it)
            }

            val intent = PendingIntent.getActivity(
                activity,
                0,
                Intent(Intent.ACTION_VIEW).apply {
                    addCategory(Intent.CATEGORY_DEFAULT)
                    flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                    setPackage(packageName)
                },
                PendingIntent.FLAG_IMMUTABLE,
            )
            val builder = NotificationCompat.Builder(activity, packageName)
                .setContentTitle("ローカルプッシュ通知")
                .setContentText("タップした際、アプリを起動する")
                .setSmallIcon(R.drawable.ic_menu_report_image)
                .setContentIntent(intent)
                .setAutoCancel(true)
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    POST_NOTIFICATIONS
                ) == PERMISSION_GRANTED
            ) {
                with(NotificationManagerCompat.from(activity)) {
                    notify(0, builder.build())
                }
                viewer.showSnackbar("ローカルプッシュ通知: 発行済み")
            } else {
                viewer.showSnackbar("通知権限を許可してください")
            }
        },
    )

    public actual fun goOsSettings(): DevMenuSpec =
        DevMenuSpec.Action("アプリのOS 設定画面へ遷移") {
            val activity = DevMenuAndroidViewer(it).activity
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = "package:${activity.packageName}".toUri()
            }
            if (intent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(intent)
            }
        }
}
