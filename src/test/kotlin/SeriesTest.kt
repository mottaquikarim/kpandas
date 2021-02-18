import com.kpandas.series.NDArray
import com.kpandas.series.Series
import org.junit.Test
import kotlin.test.assertEquals

class SeriesTest {
    @Test
    fun `series can be created`() {
        var s = Series<Any, Any>(NDArray(mutableListOf(1,2)), name ="foo")
        assertEquals(s.len(), 2)
    }

    @Test
    fun `series with index can be created`() {
        var s = Series<Any, Any>(NDArray(mutableListOf(1,2)), index=NDArray(mutableListOf("a","b")), name ="foo")
        assertEquals(s.len(), 2)
    }
}