import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
    @Test
    fun testLoadMatch() = runTest {
        assertEquals("User#1 (92%)", loadMatch(1))
    }

    @Test
    fun testLoadMatchDifferentId() = runTest {
        assertEquals("User#42 (92%)", loadMatch(42))
    }
}
