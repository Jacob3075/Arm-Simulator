package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser

interface AddInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER) -> AddRegisterInstructionParser(instructionString).invoke()
            instructionString.matches(IMMEDIATE_DEC) -> AddImmediateInstructionParser(instructionString).invoke()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
