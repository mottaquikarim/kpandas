import com.kpandas.series.Series

// https://github.com/vdmeer/asciitable
// https://github.com/mipt-npm/kmath



fun main() {
    val s = Series<Any>(mutableListOf(1,2,null, 3), name ="foo")
    var t = s * 5
    println(t)
}