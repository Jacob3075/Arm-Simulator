package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface SubInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> SubRegisterParser(instructionString).parse()
                matches(IMMEDIATE_DEC) -> SubImmediateParser(instructionString, String::immediateFromDec).parse()
                matches(IMMEDIATE_HEX) -> SubImmediateParser(instructionString, String::immediateFromHex).parse()
                else -> throw IllegalArgumentException("Cannot parse string: $this")
            }
        }
    }
}
