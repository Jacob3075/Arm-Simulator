package com.jacob.core_lib.parser.instructions.add

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Add.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Add.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Add.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface AddInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> AddRegisterParser(instructionString).parse().valid()
                matches(IMMEDIATE_DEC) -> AddImmediateParser(instructionString, String::immediateFromDec).parse()
                    .valid()
                matches(IMMEDIATE_HEX) -> AddImmediateParser(instructionString, String::immediateFromHex).parse()
                    .valid()
                else -> InvalidInstruction("Cannot parse string: $instructionString").invalidNel()
            }
        }
    }
}
