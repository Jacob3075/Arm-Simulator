package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.REGISTER
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser.Companion.None

interface AddInstructionParser : InstructionParser {

    companion object {
        private fun getInstruction(
            instructionString: String,
            shiftOperationParser: ShiftOperationParser = None
        ) = when {
            instructionString.matches(REGISTER) -> AddRegisterInstructionParser(
                instructionString,
                shiftOperationParser
            ).parse()
            instructionString.matches(IMMEDIATE_DEC) -> AddImmediateInstructionParser(
                instructionString,
                shiftOperationParser,
                String::immediateFromDec
            ).parse()
            instructionString.matches(IMMEDIATE_HEX) -> AddImmediateInstructionParser(
                instructionString,
                shiftOperationParser,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }

        fun from(instructionString: String): Instruction {
            val operationMatch = Shifts.TYPES.find(instructionString) ?: return getInstruction(instructionString)

            val instructionSubString = instructionString.substring(0 until operationMatch.range.first).trim()
            val operationSubString = instructionString.substring(startIndex = operationMatch.range.first).trim()

            val shiftOperation = ShiftOperationParser.from(operationSubString)
            return getInstruction(instructionSubString, shiftOperation)
        }
    }
}
