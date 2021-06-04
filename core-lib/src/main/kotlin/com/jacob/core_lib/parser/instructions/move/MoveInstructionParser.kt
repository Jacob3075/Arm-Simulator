package com.jacob.core_lib.parser.instructions.move

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Move.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Move.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Move.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface MoveInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> MoveRegisterParser(instructionString).parse().valid()
                matches(IMMEDIATE_DEC) -> MoveImmediateParser(instructionString, String::immediateFromDec).parse()
                    .valid()
                matches(IMMEDIATE_HEX) -> MoveImmediateParser(instructionString, String::immediateFromHex).parse()
                    .valid()
                else -> InvalidInstruction("Cannot parse instruction: $instructionString").invalidNel()
            }
        }
    }
}
