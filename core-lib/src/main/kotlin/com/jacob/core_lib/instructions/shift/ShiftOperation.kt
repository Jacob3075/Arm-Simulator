package com.jacob.core_lib.instructions.shift

sealed interface ShiftOperation {
    fun shift(value: Int, carryBit: Int): Int

    companion object None : ShiftOperation {
        override fun shift(value: Int, carryBit: Int) = value
    }
}

data class LeftShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int, carryBit: Int) = value shl shiftAmount
}

data class RightShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int, carryBit: Int) = value ushr shiftAmount
}

data class ArithmeticRightShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int, carryBit: Int) = value shr shiftAmount
}

data class RightRotateShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int, carryBit: Int) = Integer.rotateRight(value, shiftAmount)
}

data class RightRotateExtendedShift(internal val shiftAmount: Int) : ShiftOperation {
    override fun shift(value: Int, carryBit: Int): Int = TODO()
}
