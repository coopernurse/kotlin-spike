package spike

import org.junit.Assert.assertEquals
import org.junit.Test

class MathTest {

    @Test
    fun testAdd() {
        assertEquals(2, 1+1)
        assertEquals("abc", "a"+"b"+"c")
    }

}