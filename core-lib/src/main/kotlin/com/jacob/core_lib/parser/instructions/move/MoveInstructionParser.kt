package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface MoveInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String): Instruction = if (instructionString.contains('#')) {
            MoveImmediateInstructionParser(instructionString).invoke()
        } else {
            MoveRegisterInstructionParser(instructionString).invoke()
        }
    }
}
