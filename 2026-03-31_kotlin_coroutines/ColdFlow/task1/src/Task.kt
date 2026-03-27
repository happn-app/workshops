import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun connectionFlow(): Flow<String> = flow {
    val events = listOf("Connecting...", "Authenticating", "Connected", "Disconnected")
    for (event in events) {
        delay(500)
        emit(event)
    }
}

fun main() = runBlocking {
    connectionFlow()
        .filter { !it.startsWith("Connecting") }
        .map { "[$it]" }
        .collect { println(it) }
}

// [Authenticating]
// [Connected]
// [Disconnected]
