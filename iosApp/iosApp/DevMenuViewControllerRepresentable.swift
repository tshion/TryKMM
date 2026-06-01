import DevMenus
import SwiftUI
import UIKit

struct DevMenuViewControllerRepresentable: UIViewControllerRepresentable {

    private let specs: [DevMenuSpec]


    init(
        _ specs: [DevMenuSpec]
    ) {
        self.specs = specs
    }


    func makeUIViewController(
        context: Context
    ) -> UIViewController {
        return DevMenuViewControllerKt.DevMenuViewController(specs)
    }

    func updateUIViewController(
        _ uiViewController: UIViewController,
        context: Context
    ) {
    }
}
