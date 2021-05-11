package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String): Instruction = if (instructionString.contains('#')) {
            SubImmediateInstructionParser(instructionString).invoke()
        } else {
            SubRegisterInstructionParser(instructionString).invoke()
        }
    }
}
