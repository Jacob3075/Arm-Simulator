package com.jacob.core_lib.word

import com.jacob.core_lib.common.toBinaryString
import com.jacob.core_lib.common.toInt

class Word private constructor(val bitArray: Array<Bit> = Array(32) { Bit() }) {

    @OptIn(ExperimentalStdlibApi::class)
    companion object {
        fun from(intValue: Int): Word {
            val binaryString = intValue.toBinaryString(32)
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
