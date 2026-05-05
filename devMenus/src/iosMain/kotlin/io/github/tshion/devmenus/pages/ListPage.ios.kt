package io.github.tshion.devmenus.pages

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import platform.UIKit.UIUserNotificationTypeAlert
import platform.UIKit.UIUserNotificationTypeBadge
import platform.UIKit.UIUserNotificationTypeSound
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound
import platform.UserNotifications.UNTimeIntervalNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview
internal actual fun ListPage() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            item {
                Button(
                    onClick = {
                        UNUserNotificationCenter.currentNotificationCenter()
                            .requestAuthorizationWithOptions(
                                options = UIUserNotificationTypeAlert
                                    or UIUserNotificationTypeSound
                                    or UIUserNotificationTypeBadge,
                            ) { granted, error ->
                                if (!granted) {
                                    return@requestAuthorizationWithOptions
                                }

                                val request = UNNotificationRequest.requestWithIdentifier(
                                    identifier = "localNotification",
                                    content = UNMutableNotificationContent().apply {
                                        setTitle("ローカルプッシュ通知")
                                        setBody("5秒後に表示される通知です")
                                        setSound(UNNotificationSound.defaultSound)
                                    },
                                    trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(
                                        5.0,
                                        false,
                                    )
                                )
                                UNUserNotificationCenter.currentNotificationCenter()
                                    .addNotificationRequest(request) { error ->
                                        val message = if (error != null) {
                                            "$error"
                                        } else {
                                            "ローカルプッシュ通知: 発行済み"
                                        }
                                        scope.launch {
                                            snackbarHostState.showSnackbar(message)
                                        }
                                    }
                            }
                    },
                ) {
                    Text("ローカルプッシュ通知の発行")
                }
            }
        }
    }
}
