package com.kpandas.series

import de.vandermeer.asciitable.AsciiTable

class Series<out P>(val data: MutableList<Int?>? = null, private var index: List<P>? = null, val name: String? = "") {
    private val len = data?.size ?: 0

    fun len(): Int {
        return len
    }

    operator fun times(other: Int): Series<out P> {
        val t = Series(data, index = index, name = name)
        for (i in 0 until len) {
            if (data?.get(i) == null) {
                t.data?.set(i, null)
                continue
            }
            t.data?.set(i, other * data.get(i)!!)
        }
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

    fun toTable(): String {
        val at = AsciiTable()
        if (name != "") {
            at.addRule();
            at.addRow("", name)
        }
        for (i in 0 until len) {
            at.addRule()
            var dataVal = if (data?.get(i) == null) "nan" else data?.get(i)
            at.addRow(index?.get(i) ?: i, dataVal)
        }
        at.addRule()
        return at.render(30)
    }
}