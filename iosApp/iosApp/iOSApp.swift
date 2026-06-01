import DevMenus
import SwiftUI

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    @State private var isShowingSheet = false

    @StateObject private var quickActionViewModel = QuickActionViewModel.shared


    var body: some Scene {
        WindowGroup {
            MainView(viewModel: MainViewModel())
                .sheet(isPresented: $isShowingSheet) {
                    DevMenuViewControllerRepresentable(appDelegate.devMenuList)
                }
                .onReceive(quickActionViewModel.$selected) {
                    isShowingSheet = $0?.type == "showDeveloperMenuList"
                }
        }
    }
}
