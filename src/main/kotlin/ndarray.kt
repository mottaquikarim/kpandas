package com.kpandas.ndarray

import java.lang.Exception

/**
 * https://github.com/Kotlin/kotlin-numpy/blob/master/src/main/kotlin/org/jetbrains/numkt/math/ArithmeticOperators.kt
 */
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

class BooleanNDArray(override val data: List<Boolean>): NDArray<Boolean> {
    override fun size(): Int {
        return data.size
    }
    private fun applyOp(other: Boolean, cb: (Boolean, Boolean) -> Boolean): BooleanNDArray {
        val nd = mutableListOf<Boolean>()
        for (i in data.indices) {
            nd.add(cb(data[i], other))
        }
        return BooleanNDArray(nd.toList())
    }
    override  operator  fun plus(other: Boolean): BooleanNDArray = applyOp(other) { a, b -> a && b }
    override  operator  fun minus(other: Boolean): BooleanNDArray = applyOp(other) { a, b ->throw Exception("Cannot subtract booleans") }
    override  operator  fun times(other: Boolean): BooleanNDArray = applyOp(other) { a, b ->throw Exception("Cannot multiply booleans") }
    override  operator  fun div(other: Boolean): BooleanNDArray = applyOp(other) { a, b ->throw Exception("Cannot divide booleans") }
    override  operator fun get(x: Int): Boolean = data[x]
}