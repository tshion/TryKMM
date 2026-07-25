import DevMenus
import UIKit

class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        application.shortcutItems = [
            DevMenuPresenter.companion.setupShortcutItem(self.getDevMenuList())
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

    private static var repository = LogRepository()
    private static var task: Task<Void, Never>?

    func getDevMenuList() -> [DevMenuSpec] {
        return [
            DevMenuSpecItems.shared.LocalPushGroup,
            DevMenuSpecItems.shared.OsSettings,
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
            DevMenuSpec.Action("開始: ログ") {
                AppDelegate.task?.cancel()
                AppDelegate.task = nil

                let viewer = DevMenuAppleViewer($0)
                AppDelegate.task = Task {
                    for await logs in AppDelegate.repository.stream {
                        viewer.showSnackbar(
                            message: """
                                     \(logs.count) 件のログ
                                     \(logs.map { $0.composedMessage })
                                     """
                        )
                    }
                }
            },
            DevMenuSpec.Action("終了: ログ") {
                AppDelegate.task?.cancel()
                AppDelegate.task = nil
                DevMenuAppleViewer($0).dismissSnackbar()
            },
            DevMenuSpecItems.shared.DevMenuDialogSample,
        ]
    }
}
