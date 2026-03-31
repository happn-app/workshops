import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
    @Test
    fun testOffline() = runTest {
        nameFlow.value = "Alice"
        isOnlineFlow.value = false
        assertEquals("Alice • Offline", profileCardFlow().first())
    }

    @Test
    fun testOnline() = runTest {
        nameFlow.value = "Alice"
        isOnlineFlow.value = true
        assertEquals("Alice • Online", profileCardFlow().first())
    }

    @Test
    fun testDifferentName() = runTest {
        nameFlow.value = "Bob"
        isOnlineFlow.value = true
        assertEquals("Bob • Online", profileCardFlow().first())
    }
}
