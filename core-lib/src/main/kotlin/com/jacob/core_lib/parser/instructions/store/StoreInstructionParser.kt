package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.VARIABLE
import com.jacob.core_lib.parser.instructions.InstructionParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER_IMMEDIATE_OFFSET) -> StoreRegisterInstructionParser(instructionString).parse()
            instructionString.matches(REGISTER_PRE) -> StoreRegisterInstructionParser(instructionString).parse()
            instructionString.matches(REGISTER_POST) -> StoreRegisterInstructionParser(instructionString).parse()
            instructionString.matches(VARIABLE) -> StoreVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
