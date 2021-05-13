package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.InstructionRegex.Store.Companion.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER) -> StoreRegisterInstructionParser(instructionString).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
