package com.jacob.core_lib.common

fun Boolean.toInt() = if (this) 1 else 0

fun <T> List<T?>.padListTill(newLength: Int): MutableList<T?> =
    List(maxOf(newLength, size)) { index -> getOrNull(index) }.toMutableList()
