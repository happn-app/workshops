import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun fetchA(): Int {
    delay(600)
    return 42
}

suspend fun fetchB(): String {
    delay(600)
    return "hello"
}

suspend fun sequential() = coroutineScope {
    val a = fetchA()
    val b = fetchB()
    Pair(a, b)
}

suspend fun parallel() = coroutineScope {
    val deferredA = async { fetchA() }
    val deferredB = async { fetchB() }
    Pair(deferredA.await(), deferredB.await())
}

fun main() = runBlocking {
    val t1 = measureTimeMillis { sequential() }
    println("sequential: ${t1}ms")

    val t2 = measureTimeMillis { parallel() }
    println("parallel:   ${t2}ms")
}

// sequential: ~1200ms
// parallel:   ~600ms
