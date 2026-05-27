package io.github.tshion.devmenus

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * 開発メニュー画面
 */
public class DevMenuActivity : ComponentActivity() {

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

        val provider = application as? DevMenuProvider
        setContent {
            val viewModel = DevMenuSpecViewModel.create(
                specs = provider?.devMenuList,
            )
            ViewHost(viewModel)
        }
    }


    internal companion object {

        internal const val CHANNEL_ID = "io.github.tshion.devmenus"
    }
}
