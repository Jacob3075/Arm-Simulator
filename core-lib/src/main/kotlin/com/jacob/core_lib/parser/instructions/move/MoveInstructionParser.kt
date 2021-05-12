package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.InstructionRegex.Move.Companion.IMMEDIATE
import com.jacob.core_lib.common.InstructionRegex.Move.Companion.REGISTER
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MoveInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String): Instruction = when {
            instructionString.matches(REGISTER) -> MoveRegisterInstructionParser(instructionString).invoke()
            instructionString.matches(IMMEDIATE) -> MoveImmediateInstructionParser(instructionString).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
