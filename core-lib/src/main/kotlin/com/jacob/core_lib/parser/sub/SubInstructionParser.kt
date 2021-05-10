package com.jacob.core_lib.parser.sub

import com.jacob.core_lib.parser.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String): SubInstructionParser = if (instructionString.contains('#')) {
            SubImmediateInstructionParser(instructionString)
        } else {
            SubRegisterInstructionParser(instructionString)
        }
    }
}
