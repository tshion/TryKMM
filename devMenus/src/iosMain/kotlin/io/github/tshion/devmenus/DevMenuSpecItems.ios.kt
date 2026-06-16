package io.github.tshion.devmenus

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString
import platform.UIKit.UIUserNotificationTypeAlert
import platform.UIKit.UIUserNotificationTypeBadge
import platform.UIKit.UIUserNotificationTypeSound
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound
import platform.UserNotifications.UNTimeIntervalNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter

internal actual fun getLocalPushGroup(): DevMenuSpec = DevMenuSpec.Group(
    "ローカルプッシュ通知",
    getOsSettings(),
    DevMenuSpec.Action("アプリを起動する通知", "タップ後、アプリをバックグラウンドにしてください") {
        val viewer = DevMenuAppleViewer(it)
        UNUserNotificationCenter.currentNotificationCenter().requestAuthorizationWithOptions(
            options = UIUserNotificationTypeAlert or UIUserNotificationTypeSound or UIUserNotificationTypeBadge,
        ) { granted, error ->
            if (!granted) {
                viewer.showSnackbar(error?.localizedDescription ?: "")
                return@requestAuthorizationWithOptions
            }

            val request = UNNotificationRequest.requestWithIdentifier(
                identifier = "localNotification",
                content = UNMutableNotificationContent().apply {
                    setTitle("ローカルプッシュ通知")
                    setBody("タップした際、アプリを起動する")
                    setSound(UNNotificationSound.defaultSound)
                },
                trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(
                    5.0,
                    false,
                )
            )
            UNUserNotificationCenter.currentNotificationCenter()
                .addNotificationRequest(request) { error ->
                    viewer.showSnackbar(
                        error?.localizedDescription ?: "ローカルプッシュ通知: 発行済み"
                    )
                }
        }
    },
)

internal actual fun getOsSettings(): DevMenuSpec = DevMenuSpec.Action("アプリのOS 設定画面へ遷移") {
    val url = NSURL.URLWithString(UIApplicationOpenSettingsURLString)
    if (url != null && UIApplication.sharedApplication.canOpenURL(url)) {
        UIApplication.sharedApplication.openURL(url, emptyMap<Any?, Any>(), null)
    }
}
