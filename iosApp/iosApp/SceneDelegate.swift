import UIKit
import UserNotifications

class SceneDelegate: NSObject, UIWindowSceneDelegate {

    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        if let shortcutItem = connectionOptions.shortcutItem {
            // Save it off for later when we become active.
            // savedShortCutItem = shortcutItem
            print("HOGE1: \(shortcutItem)")
        }
    }

    func windowScene(
        _ windowScene: UIWindowScene,
        performActionFor shortcutItem: UIApplicationShortcutItem,
        completionHandler: @escaping (Bool) -> Void
    ) {
        if shortcutItem.type == "showDeveloperMenuList" {
            let actionSheet = UIAlertController(
                title: "開発者メニュー",
                message: nil,
                preferredStyle: .actionSheet
            )
            actionSheet.addAction(
                UIAlertAction(
                    title: "ローカルプッシュ",
                    style: .default
                ) { _ in
                    UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .sound, .badge]) { granted, error in
                        guard granted else { return }

                        let content = UNMutableNotificationContent()
                        content.title = "ローカル通知"
                        content.body = "5秒後に表示される通知です"
                        content.sound = .default

                        let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 5, repeats: false)

                        let request = UNNotificationRequest(identifier: "localNotification", content: content, trigger: trigger)
                        UNUserNotificationCenter.current().add(request) { error in
                            if let error = error {
                                print("通知エラー: \(error)")
                            }
                        }
                    }
                })
            actionSheet.addAction(
                UIAlertAction(
                    title: "閉じる",
                    style: .default
                ))

            windowScene.windows.first?.rootViewController?.present(
                actionSheet,
                animated: true,
                completion: nil
            )
        }
        completionHandler(true)
    }
}
