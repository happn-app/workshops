import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

val nameFlow = MutableStateFlow("Alice")
val isOnlineFlow = MutableStateFlow(false)

fun profileCardFlow(): Flow<String> {
    // TODO: combine nameFlow and isOnlineFlow
    // → "$name • Online" or "$name • Offline"
    return flow { }
}

fun main() = runBlocking {
    val job = launch {
        profileCardFlow().collect { println(it) }
    }
    delay(100)
    // TODO: update isOnlineFlow and nameFlow values to see the flow react
    delay(300)
    job.cancel()
}
