import kotlinx.coroutines.*

suspend fun fetchProfile(id: Int): String {
    delay(500)
    return "User#$id"
}

suspend fun fetchScore(profile: String): Int {
    delay(500)
    return 92
}

fun loadMatch(id: Int): String {
    return ""
}

fun main() = runBlocking {
    println(loadMatch(1))
    // User#1 (92%)
}
