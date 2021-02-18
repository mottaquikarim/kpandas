import com.kpandas.series.NDArray
import com.kpandas.series.DType
import com.kpandas.series.Series

fun zero(i: Int) = 5

fun main() {
//    val s = Series<String, Any>(mutableListOf("a", "b", null, "c"), name ="foo")
//    println(s)
//    var t = s * 5
//    println(t)
    val m = NDArray<Int>()
    println(m.values)
    m.add(5)
    println((m + DType(15)).values)
    println(m.values)
    val s = Series<Any, Any>(NDArray(mutableListOf(1, 2, null, 3)), NDArray(mutableListOf("a", "b", "c", "d")), name ="foo")
    println(s)
    var t = s + 5
    println(t)
}
