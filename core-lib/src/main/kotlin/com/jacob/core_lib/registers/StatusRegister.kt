package com.jacob.core_lib.registers

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.toInt
import com.jacob.core_lib.word.Word
import kotlin.math.sign

class StatusRegister : Register {
    var negative: Boolean = false
        private set
    var zero: Boolean = false
        private set
    var carry: Boolean = false
        private set
    var overFlow: Boolean = false
        private set

    override fun getRegisterValue(): Word {
        return "${negative.toInt()}${zero.toInt()}${carry.toInt()}${overFlow.toInt()}".toInt().W
    }

    fun updateCPSR(value1: Int, value2: Int, operation: Int.(Int) -> Int) {
        val result = value1.operation(value2)

        negative = value1.operation(value2).sign == -1
        zero = value1.operation(value2).sign == 0
        // FIXME
        carry = result < value1 // Subtraction
        // overFlow = (value1 xor result) and (value2 xor result) and 0x80 != 0
        // overFlow = (value1 xor value2 and 0x80).inv() and value1 xor result and 0x80 == 0
    }
}
