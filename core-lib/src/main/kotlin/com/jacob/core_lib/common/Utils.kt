package com.jacob.core_lib.common

fun Int.toBinaryString(length: Int): String {
    val result = StringBuilder()
    for (i in length - 1 downTo 0) {
        val mask = 1 shl i
        result.append(if (this and mask != 0) 1 else 0)
    }
    return result.toString()
}

fun Boolean.toInt() = if (this) 1 else 0

fun <T> List<T?>.padListTill(newLength: Int): MutableList<T?> =
    List(maxOf(newLength, size)) { index -> getOrNull(index) }.toMutableList()
