package com.jacob.core_lib.parser.instructions.move

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
                matches(REGISTER) -> MoveRegisterParser(instructionString).parse()
                matches(IMMEDIATE_DEC) -> MoveImmediateParser(instructionString, String::immediateFromDec).parse()
                matches(IMMEDIATE_HEX) -> MoveImmediateParser(instructionString, String::immediateFromHex).parse()
                else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
            }
        }
    }
}
