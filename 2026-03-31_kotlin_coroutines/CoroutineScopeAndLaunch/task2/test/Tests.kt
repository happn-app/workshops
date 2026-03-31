import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
    @Test
    fun testBothNotificationsSent() = runTest {
        notifyMatch(1, 2)
        // Both notifications must complete
        assertEquals(500L, currentTime)
    }

    @Test
    fun testNotificationsSentConcurrently() = runTest {
        notifyMatch(10, 42)
        // If sent sequentially, time would be 1000ms — concurrent must be 500ms
        assertEquals(500L, currentTime)
    }
}
