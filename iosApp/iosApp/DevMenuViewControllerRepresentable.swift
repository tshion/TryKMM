import DevMenus
import SwiftUI
import UIKit

struct DevMenuViewControllerRepresentable: UIViewControllerRepresentable {
    func makeUIViewController(
        context: Context
    ) -> UIViewController {
        return DevMenuViewControllerKt.DevMenuViewController()
    }

    func updateUIViewController(
        _ uiViewController: UIViewController,
        context: Context
    ) {
    }
}
