package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.ArithmeticRightShift
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.LeftShift
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.RightRotateExtendedShift
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.RightRotateShift
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.RightShift
import com.jacob.core_lib.instructions.shift.ShiftOperation

interface ShiftOperationParser {
    companion object {
        fun from(operationString: String) = with(operationString) {
            when {
                contains(LeftShift.MNEMONIC) -> LeftShiftOperationParser(operationString)
                contains(RightShift.MNEMONIC) -> RightShiftOperationParser(operationString)
                contains(RightRotateShift.MNEMONIC) -> RightRotateShiftOperationParser(operationString)
                contains(RightRotateExtendedShift.MNEMONIC) -> RightRotateExtendedShiftOperationParser(operationString)
                contains(ArithmeticRightShift.MNEMONIC) -> ArithmeticRightShiftOperationParser(operationString)
                else -> throw IllegalArgumentException("Cannot parse shift operation: $operationString")
            }
        }
    }

    fun parse(): ShiftOperation
}
