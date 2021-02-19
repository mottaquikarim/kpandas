//import com.kpandas.series.DType
import java.lang.Exception

interface NDArray<T> {
    val data: List<T>

    /**
     * Size of array
     */
    fun size(): Int

    /**
     * Getter
     */
    operator fun get(x: Int): T

    /**
     * Operator overloading
     */
    operator fun plus(other: T): NDArray<T>
    operator fun minus(other: T): NDArray<T>
    operator fun times(other: T): NDArray<T>
    operator fun div(other: T): NDArray<T>

}

class IntNDArray(override val data: List<Int>): NDArray<Int> {
    override fun size(): Int {
        return data.size
    }
    private fun applyOp(other: Int, cb: (Int, Int) -> Int): IntNDArray {
        val nd = mutableListOf<Int>()
        for (i in data.indices) {
            nd.add(cb(data[i], other))
        }
        return IntNDArray(nd.toList())
    }
    override  operator  fun plus(other: Int): IntNDArray = applyOp(other) { a, b -> a + b }
    override  operator  fun minus(other: Int): IntNDArray = applyOp(other) { a, b -> a - b }
    override  operator  fun times(other: Int): IntNDArray = applyOp(other) { a, b -> a * b }
    override  operator  fun div(other: Int): IntNDArray = applyOp(other) { a, b -> a / b }
    override  operator fun get(x: Int): Int = data[x]
}

class StringNDArray(override val data: List<String>): NDArray<String> {
    override fun size(): Int {
        return data.size
    }
    private fun applyOp(other: String, cb: (String, String) -> String): StringNDArray {
        val nd = mutableListOf<String>()
        for (i in data.indices) {
            nd.add(cb(data[i], other))
        }
        return StringNDArray(nd.toList())
    }
    override  operator  fun plus(other: String): StringNDArray = applyOp(other) { a, b -> a + b }
    override  operator  fun minus(other: String): StringNDArray = applyOp(other) { a, b ->throw Exception("Cannot subtract strings") }
    override  operator  fun times(other: String): StringNDArray = applyOp(other) { a, b ->throw Exception("Cannot multiply strings") }
    override  operator  fun div(other: String): StringNDArray = applyOp(other) { a, b ->throw Exception("Cannot divide strings") }
    override  operator fun get(x: Int): String = data[x]
}

class Series<T, U>(var data: NDArray<T>, private var index: NDArray<U>, val name: String? = "") {
    private val len = data.size()

    fun len(): Int {
        return len
    }

    operator fun times(other: T): Series<T, U> {
        val t = Series(data, index = index, name = name)
        t.data = data.times(other)
        return t
    }

    override fun toString(): String {
        val builder = StringBuilder()
        if (name != "") {
            builder.append("\t")
            builder.append(name)
        }

        for (i in 0 until len) {
            builder.append("\n")
            var dataVal = data[i]
            builder.append(index.get(i) ?: i)
            builder.append("\t")
            builder.append(dataVal)
        }
        builder.append("\n")
        return builder.toString()
    }
}
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
}
