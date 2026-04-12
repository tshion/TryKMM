import SwiftUI

class DeveloperMenuViewController: UIHostingController<DeveloperMenuView> {

    init() {
        super.init(rootView: DeveloperMenuView())
    }

    required dynamic init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
