package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply.REGISTER
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MultiplyInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> MultiplyRegisterInstructionParser(instructionString).parse()
                matches(IMMEDIATE_DEC) -> MultiplyImmediateInstructionParser(
                    instructionString,
                    String::immediateFromDec
                ).parse()
                matches(IMMEDIATE_HEX) -> MultiplyImmediateInstructionParser(
                    instructionString,
                    String::immediateFromHex
                ).parse()
                else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
            }
        }
    }
}
