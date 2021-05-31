package com.jacob.core_lib.registers

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.toInt
import com.jacob.core_lib.word.Word
import kotlin.math.sign

class StatusRegister(
    negative: Boolean = false,
    zero: Boolean = false,
    carry: Boolean = false,
    overFlow: Boolean = false,
) : Register {
    var negative: Boolean private set
    var zero: Boolean private set
    var carry: Boolean private set
    var overFlow: Boolean private set

    init {
        this.negative = negative
        this.zero = zero
        this.carry = carry
        this.overFlow = overFlow
    }

    override fun getRegisterValue(): Word {
        return "${negative.toInt()}${zero.toInt()}${carry.toInt()}${overFlow.toInt()}".toInt().W
    }

    fun updateCPSR(value1: Int, value2: Int, operation: Int.(Int) -> Int) {
        val result = value1.operation(value2)

        negative = value1.operation(value2).sign == -1
        zero = value1.operation(value2).sign == 0
        carry = calculateCarryBit(value1, value2, result)

//         TODO
//        overFlow = (value1.sign == value2.sign) and (value1.sign != result.sign)
    }

    private fun calculateCarryBit(value1: Int, value2: Int, result: Int): Boolean {
        val stringLength = 32

        val value1Binary = String.format("%0${stringLength}d", Integer.toBinaryString(value1)!!.toBigInteger())
        val value2Binary =
            String.format("%0${stringLength}d", Integer.toBinaryString(value2.twosComplement())!!.toBigInteger())

        val value1MSB = value1Binary.first().digitToInt()
        val value2MSB = value2Binary.first().digitToInt()
        val resultExpectedMSB = Integer.toBinaryString(value1MSB + value2MSB)!!.toInt()

        val resultBinary =
            String.format("%0${stringLength}d", Integer.toBinaryString(result)!!.toBigInteger()).takeLast(stringLength)

        val resultMSB = resultBinary.first().digitToInt()

        return resultExpectedMSB != resultMSB
    }

    private fun Int.twosComplement(): Int {
        return this.inv() + 1
    }
}
