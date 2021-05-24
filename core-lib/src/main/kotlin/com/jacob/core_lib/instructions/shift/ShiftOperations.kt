package com.jacob.core_lib.instructions.shift

import com.jacob.core_lib.common.`**`

sealed interface ShiftOperations {
    fun shift(value: Int): Int

    companion object None : ShiftOperations {
        override fun shift(value: Int) = value
    }
}

class LeftShift(private val shiftAmount: Int) : ShiftOperations {
    override fun shift(value: Int) = value * (2 `**` shiftAmount)
}

class RightShift(private val shiftAmount: Int) : ShiftOperations {
    override fun shift(value: Int) = value / (2 `**` shiftAmount)
}
