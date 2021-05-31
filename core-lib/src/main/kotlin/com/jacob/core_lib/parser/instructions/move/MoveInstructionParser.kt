package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Move.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Move.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Move.REGISTER
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MoveInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: InstructionString): Instruction = when {
            instructionString.mainInstruction.matches(REGISTER) -> MoveRegisterInstructionParser(instructionString).parse()
            instructionString.mainInstruction.matches(IMMEDIATE_DEC) -> MoveImmediateInstructionParser(
                instructionString,
                String::immediateFromDec
            ).parse()
            instructionString.mainInstruction.matches(IMMEDIATE_HEX) -> MoveImmediateInstructionParser(
                instructionString,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
