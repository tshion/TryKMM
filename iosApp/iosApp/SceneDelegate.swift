import DevMenus
import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    private let devMenuPresenter = DevMenuPresenter.companion.presenter


    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        if let item = connectionOptions.shortcutItem, devMenuPresenter.canHandle(item) {
            devMenuPresenter.handle(item)
        }
    }

    func windowScene(
        _ windowScene: UIWindowScene,
        performActionFor shortcutItem: UIApplicationShortcutItem,
        completionHandler: @escaping (Bool) -> Void
    ) {
        if devMenuPresenter.canHandle(shortcutItem) {
            devMenuPresenter.handle(shortcutItem)
        }
        completionHandler(true)
    }
}
