import SwiftUI
import TryKMMLib

struct ContentView: View {
    @State private var message: String = "..."

    var body: some View {
        Text(message).task {
            SampleModel().launchPhrase { text, error in
                message = text ?? "Failed"
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
