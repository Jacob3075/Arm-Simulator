package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface MultiplyInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> MultiplyRegisterParser(instructionString).parse()
                matches(IMMEDIATE_DEC) -> MultiplyImmediateParser(instructionString, String::immediateFromDec).parse()
                matches(IMMEDIATE_HEX) -> MultiplyImmediateParser(instructionString, String::immediateFromHex).parse()
                else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
            }
        }
    }
}
