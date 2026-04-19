package io.github.tshion.trykmp

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

public class DeveloperMenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        NotificationChannel(
            CHANNEL_ID,
            "Developer Menu",
            NotificationManager.IMPORTANCE_DEFAULT,
        ).also {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(it)
        }

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val context = LocalContext.current
                    Column {
                        Button(
                            onClick = {
                                val intent = PendingIntent.getActivity(
                                    context,
                                    0,
                                    Intent(ACTION_VIEW).apply {
                                        flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                                    },
                                    PendingIntent.FLAG_IMMUTABLE,
                                )
                                val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setContentTitle("ローカルプッシュ通知")
                                    .setContentText("5秒後に表示される通知です")
                                    .setSmallIcon(R.drawable.ic_menu_report_image)
                                    .setContentIntent(intent)
                                    .setAutoCancel(true)
                                with(NotificationManagerCompat.from(context)) {
                                    if (ActivityCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.POST_NOTIFICATIONS
                                        ) != PackageManager.PERMISSION_GRANTED
                                    ) {
                                        return@with
                                    }
                                    // notificationId is a unique int for each notification that you must define.
                                    notify(0, builder.build())
                                }
                            },
                        ) {
                            Text("ローカルプッシュ通知の発行")
                        }
                    }
                }
            }
        }
    }


    internal companion object {

        private const val CHANNEL_ID = "io.github.tshion.trykmp.developer_menu"


        fun createIntent(
            context: Context,
        ): Intent = Intent(
            Intent.ACTION_VIEW,
            Uri.EMPTY,
            context,
            DeveloperMenuActivity::class.java,
        )
    }
}
