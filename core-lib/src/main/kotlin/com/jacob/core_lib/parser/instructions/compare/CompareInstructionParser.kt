package com.jacob.core_lib.parser.instructions.compare

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface CompareInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> CompareRegisterParser(instructionString).parse().valid()
                matches(IMMEDIATE_DEC) -> CompareImmediateParser(instructionString, String::immediateFromDec).parse()
                    .valid()
                matches(IMMEDIATE_HEX) -> CompareImmediateParser(instructionString, String::immediateFromHex).parse()
                    .valid()
                else -> InvalidInstruction("Cannot parse instruction: $instructionString").invalidNel()
            }
        }
    }
}
