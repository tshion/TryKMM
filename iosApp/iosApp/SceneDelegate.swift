import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    private let quickActionViewModel = QuickActionViewModel.shared


    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        quickActionViewModel.set(connectionOptions.shortcutItem)
    }

    func windowScene(
        _ windowScene: UIWindowScene,
        performActionFor shortcutItem: UIApplicationShortcutItem,
        completionHandler: @escaping (Bool) -> Void
    ) {
        quickActionViewModel.set(shortcutItem)
        completionHandler(true)
    }
}
