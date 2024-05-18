import SwiftUI
import TryKMMLib

struct ContentView: View {
	var body: some View {
		Text(Greeting().greet().joined())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
