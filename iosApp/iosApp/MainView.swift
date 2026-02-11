import SwiftUI
import TryKMP

struct MainView: View {
    @State private var isTapped = false
    @ObservedObject private(set) var viewModel: MainViewModel

    var body: some View {
        VStack {
            Text(viewModel.timeText)
            Divider()
            if !isTapped {
                Button("Search GitHub Repo"){
                    isTapped = !isTapped
                }.task {
                    await viewModel.searchRepo()
                }
            } else {
                Text(viewModel.repos)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .onAppear(perform: {
            viewModel.startTimer()
        })
        .onDisappear(perform: {
            viewModel.stopTimer()
        })
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView(viewModel: MainViewModel())
    }
}
