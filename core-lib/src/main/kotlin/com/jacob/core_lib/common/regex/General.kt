package com.jacob.core_lib.common.regex

class General private constructor() {
    class Headers private constructor() {
        companion object {
            val DATA = "^.DATA$".toRegex() // .DATA
            val END = "^.END$".toRegex() // .END
            val TEXT = "^.TEXT$".toRegex() // .TEXT
        }
    }

    companion object {
        val WORD = "^[A-Z]+: ?.WORD( \\d*)?".toRegex() // .WORD
    }
}
