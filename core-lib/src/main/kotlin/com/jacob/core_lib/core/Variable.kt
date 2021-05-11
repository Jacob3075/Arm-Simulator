package com.jacob.core_lib.core

import com.jacob.core_lib.word.Word

data class Variable(val name: String, val value: Int) {
    val word = Word(value)
}
