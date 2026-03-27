import kotlinx.coroutines.*

fun blockingWork(name: String) {
    println("$name starts  [thread: ${Thread.currentThread().name}]")
    Thread.sleep(1000)
    println("$name ends    [thread: ${Thread.currentThread().name}]")
}

suspend fun suspendWork(name: String) {
    println("$name starts  [thread: ${Thread.currentThread().name}]")
    delay(1000)
    println("$name ends    [thread: ${Thread.currentThread().name}]")
}

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")

    println("=== Blocking ===")
    blockingWork("A")
    blockingWork("B")

    println("\n=== Suspending ===")
    runBlocking {
        suspendWork("A")
        suspendWork("B")
    }
}

// === Blocking ===
// A starts  [thread: main]
// A ends    [thread: main]
// B starts  [thread: main]
// B ends    [thread: main]
//
// === Suspending ===
// A starts  [thread: main @coroutine#1]
// A ends    [thread: main @coroutine#1]
// B starts  [thread: main @coroutine#1]
// B ends    [thread: main @coroutine#1]
