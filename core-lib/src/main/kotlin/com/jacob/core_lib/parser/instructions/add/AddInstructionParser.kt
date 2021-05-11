package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface AddInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String): Instruction = if (instructionString.contains('#')) {
            AddImmediateInstructionParser(instructionString).invoke()
        } else {
            AddRegisterInstructionParser(instructionString).invoke()
        }
    }
}
