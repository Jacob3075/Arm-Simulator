package com.jacob.core_lib.parser.instructions.sub

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
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
                matches(REGISTER) -> SubRegisterParser(instructionString).parse().valid()
                matches(IMMEDIATE_DEC) -> SubImmediateParser(instructionString, String::immediateFromDec).parse()
                    .valid()
                matches(IMMEDIATE_HEX) -> SubImmediateParser(instructionString, String::immediateFromHex).parse()
                    .valid()
                else -> InvalidInstruction("Cannot parse string: $this").invalidNel()
            }
        }
    }
}
