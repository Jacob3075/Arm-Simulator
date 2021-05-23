package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.VARIABLE
import com.jacob.core_lib.parser.instructions.InstructionParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER) -> StoreRegisterInstructionParser(instructionString).parse()
            instructionString.matches(VARIABLE) -> StoreVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
