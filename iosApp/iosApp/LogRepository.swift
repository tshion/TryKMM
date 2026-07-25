import OSLog

class LogRepository {

    var stream: AsyncStream<[OSLogEntry]> {
        AsyncStream { continuation in
            let task = Task {
                guard let store = try? OSLogStore(scope: .currentProcessIdentifier) else {
                    continuation.finish()
                    return
                }

                var date = Date.now
                while !Task.isCancelled {
                    if let entries = try? store.getEntries(at: store.position(date: date)) {
                        let logs = entries.filter { date < $0.date }.compactMap { $0 }
                        if let last = logs.max(by: { $0.date < $1.date }) {
                            date = last.date
                        }
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
