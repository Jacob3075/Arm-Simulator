package com.jacob.core_lib.parser

interface Line {
    companion object {
        fun from(line: String): Line {
            TODO()
        }
    }

    fun parse(): Any
}
