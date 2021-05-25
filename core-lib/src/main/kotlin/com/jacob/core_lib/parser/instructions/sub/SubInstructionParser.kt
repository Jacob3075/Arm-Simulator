package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.REGISTER
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.instructions.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = InstructionParser.from(instructionString, ::getInstruction)

        private fun getInstruction(
            instructionString: String,
            shiftOperation: ShiftOperation = ShiftOperation.None
        ) = when {
            instructionString.matches(REGISTER) -> SubRegisterInstructionParser(
                instructionString,
                shiftOperation
            ).parse()
            instructionString.matches(IMMEDIATE_DEC) -> SubImmediateInstructionParser(
                instructionString,
                shiftOperation,
                String::immediateFromDec
            ).parse()
            instructionString.matches(IMMEDIATE_HEX) -> SubImmediateInstructionParser(
                instructionString,
                shiftOperation,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
