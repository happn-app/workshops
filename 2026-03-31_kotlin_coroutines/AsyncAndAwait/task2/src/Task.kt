import kotlinx.coroutines.*

suspend fun fetchProfile(userId: Int): String {
    delay(300)
    return "User#$userId"
}

suspend fun fetchCompatibility(userId: Int): Int {
    delay(300)
    return 87
}

suspend fun loadMatchCard(userId: Int): Pair<String, Int> = coroutineScope {
    // TODO: fetch profile and compatibility concurrently, return Pair(profile, compatibility)
    Pair("", 0)
}

fun main() = runBlocking {
    val (profile, score) = loadMatchCard(42)
    println("$profile — $score%")
    // User#42 — 87%
}
