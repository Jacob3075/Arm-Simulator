package com.jacob.core_lib.word

import com.jacob.core_lib.common.toBinaryString
import com.jacob.core_lib.common.toInt

class Word private constructor(val bitArray: Array<Bit> = Array(32) { Bit() }) {
    enum class WordSize(val wordSize: Int) {
        BIT32(32), BIT16(16), BIT8(8);
    }

    @OptIn(ExperimentalStdlibApi::class)
    companion object {
        fun from(intValue: Int, wordSize: WordSize = WordSize.BIT32): Word {
            val binaryString = intValue.toBinaryString(wordSize.wordSize)
            val bits = binaryString
                .map {
                    if (it.digitToInt() == 1) {
                        return@map Bit(true)
                    } else {
                        return@map Bit(false)
                    }
                }
            return Word(bits.toTypedArray())
        }
    }

    fun toInt(): Int {
        val bitString = bitArray.joinToString("") {
            it.value.toInt().toString()
        }
        return Integer.parseInt(bitString, 2)
    }
}
