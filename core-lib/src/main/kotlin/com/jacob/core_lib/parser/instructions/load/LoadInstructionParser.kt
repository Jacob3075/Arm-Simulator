package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.regex.InstructionRegex.Load.Companion.REGISTER
import com.jacob.core_lib.common.regex.InstructionRegex.Load.Companion.VARIABLE
import com.jacob.core_lib.parser.instructions.InstructionParser

interface LoadInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER) -> LoadRegisterInstructionsParser(instructionString).parse()
            instructionString.matches(VARIABLE) -> LoadVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
