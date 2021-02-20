import com.kpandas.ndarray.IntNDArray
import com.kpandas.series.*
import com.kpandas.ndarray.*
import org.junit.Test
import kotlin.test.assertEquals

class SeriesTest {
    @Test
    fun `series can be created`() {
        var s = Series<Int, Any>(IntNDArray(listOf(1, 2)), name = "foo")
        assertEquals(s.len(), 2)
    }

    @Test
    fun `series with index can be created`() {
        var s = Series<Int, String>(IntNDArray(listOf(1, 2)), index = StringNDArray(listOf("a", "b")), name = "foo")
        assertEquals(s.len(), 2)
    }
}