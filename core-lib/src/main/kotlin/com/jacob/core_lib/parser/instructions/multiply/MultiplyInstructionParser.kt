package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MultiplyInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String) = InstructionParser.from(instructionString, ::getInstruction)

        private fun getInstruction(
            instructionString: String,
            shiftOperation: ShiftOperation = ShiftOperation.None
        ) = when {
            instructionString.matches(InstructionRegex.Multiply.REGISTER) -> MultiplyRegisterInstructionParser(
                instructionString,
                shiftOperation
            ).parse()
            instructionString.matches(InstructionRegex.Multiply.IMMEDIATE_DEC) -> MultiplyImmediateInstructionParser(
                instructionString,
                shiftOperation,
                String::immediateFromDec
            ).parse()
            instructionString.matches(InstructionRegex.Multiply.IMMEDIATE_HEX) -> MultiplyImmediateInstructionParser(
                instructionString,
                shiftOperation,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
