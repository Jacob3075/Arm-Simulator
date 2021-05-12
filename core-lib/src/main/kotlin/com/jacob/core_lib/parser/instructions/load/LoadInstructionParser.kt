package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.parser.instructions.InstructionParser

interface LoadInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = if (instructionString.contains('=')) {
            LoadVariableInstructionParser(instructionString).invoke()
        } else {
            LoadRegisterInstructionsParser(instructionString).invoke()
        }
    }
}
