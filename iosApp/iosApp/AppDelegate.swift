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

    func getDevMenuList() -> [DevMenuSpec] {
        let goSettingsAction = DevMenuSpec.Action("アプリのOS 設定画面へ遷移") { _ in
            if let url = URL(string: UIApplication.openSettingsURLString) {
                UIApplication.shared.open(url, options: [:], completionHandler: nil)
            }
        }
        return [
            DevMenuSpec.Group(
                "ローカルプッシュ通知",
                [
                    goSettingsAction,
                    DevMenuSpec.Action("アプリを起動する通知") {
                        let viewer = DevMenuAppleViewer($0)
                        UNUserNotificationCenter.current().requestAuthorization(
                            options: [.alert, .sound, .badge]
                        ) { granted, error in
                            guard granted else {
                                viewer.showSnackbar(message: error?.localizedDescription ?? "")
                                return
                            }

                            let content = UNMutableNotificationContent()
                            content.title = "ローカルプッシュ通知"
                            content.body = "タップした際、アプリを起動する"
                            content.sound = .default

                            let request = UNNotificationRequest(
                                identifier: "localNotification",
                                content: content,
                                trigger: UNTimeIntervalNotificationTrigger(
                                    timeInterval: 0,
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
                    },
                ],
            ),
            goSettingsAction,
            DevMenuSpec.Action("アプリ側のダイアログ表示") {
                let viewer = DevMenuAppleViewer($0)
                let alert = UIAlertController(
                    title: "",
                    message: "購入しますか？",
                    preferredStyle: .alert
                )
                alert.addAction(
                    UIAlertAction(title: "OK", style: .default, handler: nil)
                )
                viewer.viewController.present(alert, animated: true, completion: nil)
            },
        ]
    }
}
