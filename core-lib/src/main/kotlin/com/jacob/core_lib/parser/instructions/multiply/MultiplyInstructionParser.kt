package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MultiplyInstructionParser : InstructionParser {

    companion object {
        private fun from(
            instructionString: String,
        ) = when {
            instructionString.matches(InstructionRegex.Multiply.REGISTER) -> MultiplyRegisterInstructionParser(
                instructionString,
            ).parse()
            instructionString.matches(InstructionRegex.Multiply.IMMEDIATE_DEC) -> MultiplyImmediateInstructionParser(
                instructionString,
                String::immediateFromDec
            ).parse()
            instructionString.matches(InstructionRegex.Multiply.IMMEDIATE_HEX) -> MultiplyImmediateInstructionParser(
                instructionString,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
