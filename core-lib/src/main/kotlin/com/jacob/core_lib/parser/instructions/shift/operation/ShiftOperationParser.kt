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
                isEmpty() -> ShiftOperation.None
                isBlank() -> ShiftOperation.None
                contains(LeftShift.MNEMONIC) -> LeftShiftParser(operationString).parse()
                contains(RightShift.MNEMONIC) -> RightShiftParser(operationString).parse()
                contains(RightRotateShift.MNEMONIC) -> RightRotateShiftParser(operationString).parse()
                contains(RightRotateExtendedShift.MNEMONIC) -> RightRotateExtendedShiftParser(operationString).parse()
                contains(ArithmeticRightShift.MNEMONIC) -> ArithmeticRightShiftParser(operationString).parse()
                else -> throw IllegalArgumentException("Cannot parse shift operation: $operationString")
            }
        }
    }

    fun parse(): ShiftOperation
}
