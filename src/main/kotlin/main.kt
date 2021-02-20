//import com.kpandas.series.DType

import com.kpandas.ndarray.*
import com.kpandas.series.*

fun main() {
    var t = IntNDArray(listOf(1,2,3,4))
    println(t.data)
    var u = t + 5
    println(u.data)

    var a = StringNDArray(listOf("a", "b", "c", "D"))
    println(a.data)
    var b = a + "FOOBAR"
    println(b.data)

    var s = Series<Int, String>(t, a, "fooobar")
    println(s)
    var s1 = s * 5
    println(s1)
    println(s1.eq(5))
    println(s1.neq(5))
}
