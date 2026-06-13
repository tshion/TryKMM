package io.github.tshion.devmenus

import platform.Foundation.NSURL.Companion.URLWithString
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

public actual object DevMenuSpecItems {
    public actual fun goLocalPushGroup(): DevMenuSpec = DevMenuSpec.Group(
        "ローカルプッシュ通知",
        goOsSettings(),
        DevMenuSpec.Action("アプリを起動する通知") {
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
                        setBody("5秒後に表示される通知です")
                        setSound(UNNotificationSound.defaultSound)
                    },
                    trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(
                        3.0,
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

    public actual fun goOsSettings(): DevMenuSpec =
        DevMenuSpec.Action("アプリのOS 設定画面へ遷移") {
            URLWithString(UIApplicationOpenSettingsURLString)?.also {
                UIApplication.sharedApplication.openURL(it)
            }
        }
}
