package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.REGISTER
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.instructions.InstructionParser

interface AddInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String) = InstructionParser.from(instructionString, ::getInstruction)

        private fun getInstruction(
            instructionString: String,
            shiftOperation: ShiftOperation = ShiftOperation.None
        ) = when {
            instructionString.matches(REGISTER) -> AddRegisterInstructionParser(
                instructionString,
                shiftOperation
            ).parse()
            instructionString.matches(IMMEDIATE_DEC) -> AddImmediateInstructionParser(
                instructionString,
                shiftOperation,
                String::immediateFromDec
            ).parse()
            instructionString.matches(IMMEDIATE_HEX) -> AddImmediateInstructionParser(
                instructionString,
                shiftOperation,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
