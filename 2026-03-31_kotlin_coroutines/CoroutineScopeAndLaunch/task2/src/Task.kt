import kotlinx.coroutines.*

suspend fun sendNotification(userId: Int) {
    delay(500)
    println("Notification sent to User#$userId")
}

suspend fun notifyMatch(userId1: Int, userId2: Int) = coroutineScope {
    // TODO: notify both users concurrently using launch
}

fun main() = runBlocking {
    notifyMatch(1, 2)
}
