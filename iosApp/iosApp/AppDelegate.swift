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
            DevMenuSpecItems.shared.DevMenuDialogSample,
        ]
    }
}
