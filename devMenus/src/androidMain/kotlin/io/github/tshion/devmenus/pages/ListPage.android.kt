package io.github.tshion.devmenus.pages

import android.Manifest
import android.R
import android.app.PendingIntent
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview
internal actual fun ListPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("開発者メニュー")
                }
            )
        },
    ) { innerPadding ->
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            item {
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
                        val builder =
                            NotificationCompat.Builder(context, "DevMenuActivity.CHANNEL_ID")
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
