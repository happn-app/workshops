import kotlinx.coroutines.*

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")
    runBlocking {
        val job = launch {
            println("A starts [${Thread.currentThread().name}]")
            delay(1000)
            println("A ends   [${Thread.currentThread().name}]")
        }

        println("B runs   [${Thread.currentThread().name}]")
        delay(500)
        println("B done   [${Thread.currentThread().name}]")

        job.join()
        println("all done")
    }
}

// A starts [main @coroutine#2]
// B runs   [main @coroutine#1]
// B done   [main @coroutine#1]
// A ends   [main @coroutine#2]
// all done
