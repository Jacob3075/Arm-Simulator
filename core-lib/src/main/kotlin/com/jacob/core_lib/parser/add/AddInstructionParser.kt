package com.jacob.core_lib.parser.add

import com.jacob.core_lib.parser.InstructionParser

interface AddInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String): AddInstructionParser = if (instructionString.contains('#')) {
            AddImmediateInstructionParser(instructionString)
        } else {
            AddRegisterInstructionParser(instructionString)
        }
    }
}
