import SwiftUI
import UserNotifications

struct DeveloperMenuView: View {

    @State private var message = ""
    @State private var timer: Timer?

    var body: some View {
        NavigationStack {
            List {
                HStack {
                    Text("ローカルプッシュ通知の発行")
                    Spacer()
                }
                .contentShape(Rectangle())
                .onTapGesture {
                    UNUserNotificationCenter.current().requestAuthorization(
                        options: [.alert, .sound, .badge]
                    ) { granted, error in
                        guard granted else { return }

                        let content = UNMutableNotificationContent()
                        content.title = "ローカルプッシュ通知"
                        content.body = "5秒後に表示される通知です"
                        content.sound = .default

                        let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 5, repeats: false)

                        let request = UNNotificationRequest(identifier: "localNotification", content: content, trigger: trigger)
                        UNUserNotificationCenter.current().add(request) { error in
                            DispatchQueue.main.async {
                                if let error {
                                    message = "\(error)"
                                } else {
                                    message = "ローカルプッシュ通知: 発行済み"
                                }
                                timer?.invalidate()
                                timer = Timer.scheduledTimer(withTimeInterval: 2, repeats: false) { _ in
                                    message = ""
                                }
                            }
                        }
                    }
                }
            }
            .navigationTitle("開発者メニュー")
            .overlay(
                Group {
                    if !message.isEmpty {
                        Text(message)
                    }
                },
                alignment: .bottom
            )
        }
        .onDisappear {
            message = ""
            timer?.invalidate()
            timer = nil
        }
    }
}
