package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser

interface InstructionParser {

    fun parse(): Instruction

    companion object {
        fun from(instructionString: String, getInstruction: (String, ShiftOperation) -> Instruction): Instruction {
            val operationMatch =
                InstructionRegex.Shifts.TYPES.find(instructionString) ?: return getInstruction(
                    instructionString,
                    ShiftOperation.None
                )

            val instructionSubString = instructionString.substring(0 until operationMatch.range.first).trim()
            val operationSubString = instructionString.substring(startIndex = operationMatch.range.first).trim()

            val shiftOperation = ShiftOperationParser.from(operationSubString).parse()
            return getInstruction(instructionSubString, shiftOperation)
        }
    }
}
