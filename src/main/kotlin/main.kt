import com.kpandas.series.Series

fun main() {
    val s = Series<Any>(mutableListOf(1,2,null, 3), name ="foo")
    println(s)
    var t = s * 5
    println(t)
}