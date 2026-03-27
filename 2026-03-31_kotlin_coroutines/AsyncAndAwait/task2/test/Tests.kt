import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
    @Test
    fun testLoadMatchCard() = runTest {
        val (profile, score) = loadMatchCard(42)
        assertEquals("User#42", profile)
        assertEquals(87, score)
    }

    @Test
    fun testLoadMatchCardConcurrent() = runTest {
        loadMatchCard(1)
        assertEquals(300L, currentTime)
    }
}
