import SwiftUI
import TryKMP

@MainActor
class MainViewModel: ObservableObject {

    private let model: TryKmp

    @Published private(set) var repos = ""

    private var timer: Timer? = nil

    @Published private(set) var timeText = ""


    init(_ model: TryKmp) {
        self.model = model
        timeText = model.time(suffix: "")
    }

    convenience init() {
        self.init(TryKmp())
    }


    func searchRepo() async {
        repos = "LOADING..."

        let query = "ios"
        let result = try! await model.searchGitHubRepo(query: query)
        repos = result
    }

    func startTimer() {
        stopTimer()
        timer = Timer.scheduledTimer(
            withTimeInterval: 2.5,
            repeats: true
        ) { _ in
            DispatchQueue.main.async {
                self.timeText = self.model.time(suffix: "")
            }
        }
    }

    func stopTimer() {
        timer?.invalidate()
        timer = nil
    }
}
