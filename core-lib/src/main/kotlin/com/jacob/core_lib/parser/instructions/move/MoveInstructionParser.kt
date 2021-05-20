package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.regex.InstructionRegex.Move.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Move.Companion.REGISTER
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MoveInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String): Instruction = when {
            instructionString.matches(REGISTER) -> MoveRegisterInstructionParser(instructionString).invoke()
            instructionString.matches(IMMEDIATE_DEC) -> MoveImmediateInstructionParser(instructionString).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
