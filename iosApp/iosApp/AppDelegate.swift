import DevMenus
import UIKit

class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        application.shortcutItems = [
            UIApplicationShortcutItem(
                type: "showDeveloperMenuList",
                localizedTitle: "開発者メニューの表示",
                localizedSubtitle: nil,
                icon: UIApplicationShortcutIcon(systemImageName: "magnifyingglass"),
                userInfo: nil
            )
        ]
        return true
    }

    func application(
        _ application: UIApplication,
        configurationForConnecting connectingSceneSession: UISceneSession,
        options: UIScene.ConnectionOptions
    ) -> UISceneConfiguration {
        let configuration = UISceneConfiguration(
            name: connectingSceneSession.configuration.name,
            sessionRole: connectingSceneSession.role
        )
        configuration.delegateClass = SceneDelegate.self
        return configuration
    }
}

extension AppDelegate: DevMenuProvider {

    var devMenuList: [DevMenuSpec] {
        return [
            DevMenuSpec.Group(
                "ディープリンク",
                [
                    DevMenuSpec.Action("通常のアプリ起動") { viewer in
                        UNUserNotificationCenter.current().requestAuthorization(
                            options: [.alert, .sound, .badge]
                        ) { granted, error in
                            guard granted else {
                                viewer.showSnackbar(message: error?.localizedDescription ?? "")
                                return
                            }

                            let content = UNMutableNotificationContent()
                            content.title = "ローカルプッシュ通知"
                            content.body = "5秒後に表示される通知です"
                            content.sound = .default
                            
                            let request = UNNotificationRequest(
                                identifier: "localNotification",
                                content: content,
                                trigger: UNTimeIntervalNotificationTrigger(
                                    timeInterval: 5.0,
                                    repeats: false
                                )
                            )
                            UNUserNotificationCenter.current().add(request) { error in
                                if let error = error {
                                    viewer.showSnackbar(message: error.localizedDescription)
                                } else {
                                    viewer.showSnackbar(message: "ローカルプッシュ通知: 発行済み")
                                }
                            }
                        }
                    }
                ]
            ),
            DevMenuSpec.Action("a") { _ in
            },
            DevMenuSpec.Action("b", "c") { _ in
            },
        ]
    }
}
