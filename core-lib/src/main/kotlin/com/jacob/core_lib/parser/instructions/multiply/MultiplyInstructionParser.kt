package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MultiplyInstructionParser : InstructionParser {

    companion object {
        fun from(
            instructionString: InstructionString,
        ) = when {
            instructionString.coreInstruction.matches(InstructionRegex.Multiply.REGISTER) -> MultiplyRegisterInstructionParser(
                instructionString,
            ).parse()
            instructionString.coreInstruction.matches(InstructionRegex.Multiply.IMMEDIATE_DEC) -> MultiplyImmediateInstructionParser(
                instructionString,
                String::immediateFromDec
            ).parse()
            instructionString.coreInstruction.matches(InstructionRegex.Multiply.IMMEDIATE_HEX) -> MultiplyImmediateInstructionParser(
                instructionString,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
