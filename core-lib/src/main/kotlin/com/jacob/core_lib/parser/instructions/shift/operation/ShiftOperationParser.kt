package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.Companion.LeftShift
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.Companion.RightShift
import com.jacob.core_lib.instructions.shift.ShiftOperation

interface ShiftOperationParser {
    companion object {
        fun from(operationString: String): ShiftOperationParser {
            return when {
                operationString.contains(LeftShift.MNEMONIC) -> LeftShiftOperationParser(operationString)
                operationString.contains(RightShift.MNEMONIC) -> RightShiftOperationParser(operationString)
                else -> throw IllegalArgumentException()
            }
        }

        object None : ShiftOperationParser {
            override fun parse() = ShiftOperation.None
        }
    }

    fun parse(): ShiftOperation
}
