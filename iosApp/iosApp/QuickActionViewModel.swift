import UIKit

class QuickActionViewModel: ObservableObject {

    static let shared = QuickActionViewModel()


    private init() {}


    @Published private(set) var selected: UIApplicationShortcutItem?


    func set(_ shortcutItem: UIApplicationShortcutItem?) {
        if let item = shortcutItem {
            selected = item
        }
    }
}
