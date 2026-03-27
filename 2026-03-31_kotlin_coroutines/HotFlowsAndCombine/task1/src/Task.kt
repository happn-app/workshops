import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

val itemCount = MutableStateFlow(0)
val promoActive = MutableStateFlow(false)

val cartSummary: Flow<String> = combine(itemCount, promoActive) { count, promo ->
    val promoText = if (promo) " (promo active!)" else ""
    "$count item(s)$promoText"
}

fun main() = runBlocking {
    val job = launch {
        cartSummary.collect { println(it) }
    }

    delay(100)
    itemCount.value = 2
    delay(100)
    promoActive.value = true
    delay(100)
    itemCount.value = 5
    delay(100)

    job.cancel()
}

// 0 item(s)
// 2 item(s)
// 2 item(s) (promo active!)
// 5 item(s) (promo active!)
