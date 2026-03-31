import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
    @Test
    fun testLikesFlow() = runTest {
        val ids = likesFlow().toList()
        assertEquals(listOf(1, 2, 3, 4, 5), ids)
    }

    @Test
    fun testNotificationsFlow() = runTest {
        val notifications = notificationsFlow().toList()
        assertEquals(
            listOf(
                "User#1 liked you!",
                "User#2 liked you!",
                "User#3 liked you!",
                "User#4 liked you!",
                "User#5 liked you!"
            ),
            notifications
        )
    }
}
