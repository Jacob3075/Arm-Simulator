package com.jacob.core_lib.instructions.shift

import com.jacob.core_lib.common.`**`

sealed interface ShiftOperation {
    fun shift(value: Int): Int

    companion object None : ShiftOperation {
        override fun shift(value: Int) = value
    }
}

data class LeftShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int) = value * (2 `**` shiftAmount)
}

data class RightShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int) = value / (2 `**` shiftAmount)
}

data class RightRotateShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int) = TODO()
}

data class RightRotateExtendedShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int) = TODO()
}

data class ArithmeticRightShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int) = TODO()
}
