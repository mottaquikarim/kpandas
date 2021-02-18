package com.kpandas.series

import java.lang.Exception

// inspired by:
// https://github.com/Kotlin/kotlin-numpy/blob/master/src/main/kotlin/org/jetbrains/numkt/math/ArithmeticOperators.kt
class DType<R: Any>(val data: Any?) {
    operator fun plus(element: DType<Any>): R? {
        if (data == null) {
            return null
        }
        return when(data) {
            is Int -> {
                when (element.data) {
                    is Long -> (data + element.data) as R
                    is Float -> (data + element.data) as R
                    is Int -> (data + element.data) as R
                    else -> throw Exception("Cannot add Int with non numeric type")
                }
            }
            else -> throw Exception("Cannot add unrecognized type")
        }
    }
}
class NDArray<T>(x: MutableList<T?>? = null) {
    var values: MutableList<T> = (x ?: mutableListOf()) as MutableList<T>
    // TODO: back away from this method, nd arrays are immutable
    fun add(element: T) {
        values.add(element)
    }
    fun size(): Int {
        return values.size
    }
    operator fun T.plus(element: DType<Any>): T {
        return (DType<Any>(this) + element) as T
    }
    operator fun plus(other: DType<Any>): NDArray<T> {
        val nd = NDArray<T>()
        for (i in values.indices) {
            nd.add(this.values[i] + other)
        }
        return nd
    }
    operator fun get(x: Int) = values[x]
    operator fun set(x: Int, value: T) = run { values[x] = value }
}

class Series<T, U>(var data: NDArray<T?>? = null, private var index: NDArray<U>? = null, val name: String? = "") {
    private val len = data?.size() ?: 0

    fun len(): Int {
        return len
    }

    operator fun plus(other: T): Series<T, U> {
        val t = Series(data, index = index, name = name)
        t.data = data?.plus(DType(other))
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
            var dataVal = if (data?.get(i) == null) "nan" else data?.get(i)
            builder.append(index?.get(i) ?: i)
            builder.append("\t")
            builder.append(dataVal)
        }
        builder.append("\n")
        return builder.toString()
    }
}

// private operator fun <T> T.times(other: T): T? {
//    return when (this) {
//        is Long   -> this.toLong() * other.toLong()
//        is Int    -> this.toInt()  * other.toInt()
//        is Short  -> this.toShort() * other.toShort()
//        is Byte   -> this.toByte() * other.toByte()
//        is Double -> this.toDouble() * other.toDouble()
//        is Float  -> this.toFloat() * other.toFloat()
//        else      -> throw RuntimeException("Unknown numeric type")
//    }
// }
//
// private fun <T> T.toLong(): Long {
//    TODO("Not yet implemented")
// }
