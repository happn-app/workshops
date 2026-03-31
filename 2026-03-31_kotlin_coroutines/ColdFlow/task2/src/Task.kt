import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun likesFlow(): Flow<Int> = flow {
    // TODO: emit user IDs 1 to 5 with a 100ms delay between each
}

fun notificationsFlow(): Flow<String> {
    // TODO: reuse likesFlow() and transform each id into "User#$id liked you!"
    return flow { }
}

fun main() = runBlocking {
    notificationsFlow().collect { println(it) }
    // User#1 liked you!
    // User#2 liked you!
    // User#3 liked you!
    // User#4 liked you!
    // User#5 liked you!
}
