import com.kpandas.series.NDArray
import com.kpandas.series.DType
import com.kpandas.series.Series

fun zero(i: Int) = 5

fun main() {
    // create new NDArray
    val m = NDArray<Int>()
    println(m.values)
    // TODO: remove this, ndarrays are immutable
    m.add(5)
    println((m + DType(15)).values)
    println(m.values)
    // create series objects from ndarrays
    // TODO: take a mutableList and convert to NDArray behind the scenes
    val s = Series<Any, Any>(NDArray(mutableListOf(1, 2, null, 3)), NDArray(mutableListOf("a", "b", "c", "d")), name ="foo")
    println(s)
    var t = s + 5
    println(t)
}
