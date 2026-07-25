import OSLog

class LogRepository {

    var stream: AsyncStream<[OSLogEntry]> {
        AsyncStream { continuation in
            let task = Task {
                guard let store = try? OSLogStore(scope: .currentProcessIdentifier) else {
                    continuation.finish()
                    return
                }

                while !Task.isCancelled {
                    if let entries = try? store.getEntries() {
                        let logs = entries.compactMap { $0 }
                        continuation.yield(logs)
                    }
                    do {
                        try await Task.sleep(for: .seconds(1))
                    } catch {
                        break
                    }
                }
                continuation.finish()
            }

            continuation.onTermination = { _ in
                task.cancel()
            }
        }
    }
}
