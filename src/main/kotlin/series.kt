package com.kpandas.series

import com.kpandas.ndarray.*

class Series<T, U>(
    var data: NDArray<T>,
    private var index: NDArray<U>? = null,
    val name: String? = ""
) {
    private val len = data.size()

    fun len(): Int {
        return len
    }

    private fun applyOp(other: T, cb: (other: T) -> NDArray<T>): Series<T, U> {
        val t = Series(data, index = index, name = name)
        t.data = cb(other)
        return t
    }
    operator fun times(other: T): Series<T, U> = applyOp(other) { a -> data.times(a) }
    operator fun minus(other: T): Series<T, U> = applyOp(other) { a -> data.minus(a) }
    operator fun plus(other: T): Series<T, U> = applyOp(other) { a -> data.plus(a) }
    operator fun div(other: T): Series<T, U> = applyOp(other) { a -> data.div(a) }

    private fun applyCmp(other: T, cb: (other: T, i: Int) -> Boolean): Series<Boolean, U> {
        var t = mutableListOf<Boolean>()
        for (i in 0..data.data.size - 1) {
            t.add(cb(other, i))
        }

        return Series(BooleanNDArray(t.toList()), index = index, name = name)
    }
    fun eq(other: T): Series<Boolean, U> = applyCmp(other) { a, i -> data[i] == other }
    fun neq(other: T): Series<Boolean, U> = applyCmp(other) { a, i -> data[i] != other }

    override fun toString(): String {
        val builder = StringBuilder()
        if (name != "") {
            builder.append("\t")
            builder.append(name)
        }

        for (i in 0 until len) {
            builder.append("\n")
            var dataVal = data[i]
            builder.append(if (index?.get(i) != null) index?.get(i) else "nan")
            builder.append("\t")
            builder.append(dataVal)
        }
        builder.append("\n")
        return builder.toString()
    }
}
