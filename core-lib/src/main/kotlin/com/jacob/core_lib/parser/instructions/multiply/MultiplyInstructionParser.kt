package com.jacob.core_lib.parser.instructions.multiply

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
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
                matches(REGISTER) -> MultiplyRegisterParser(instructionString).parse().valid()
                matches(IMMEDIATE_DEC) -> MultiplyImmediateParser(instructionString, String::immediateFromDec).parse()
                    .valid()
                matches(IMMEDIATE_HEX) -> MultiplyImmediateParser(instructionString, String::immediateFromHex).parse()
                    .valid()
                else -> InvalidInstruction("Cannot parse string: $instructionString").invalidNel()
            }
        }
    }
}
