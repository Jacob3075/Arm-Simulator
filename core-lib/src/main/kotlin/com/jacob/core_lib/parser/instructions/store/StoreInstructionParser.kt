package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.VARIABLE
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.load.LoadRegisterPostOffsetParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER_IMMEDIATE_OFFSET) -> StoreRegisterImmediateOffsetParser(instructionString).parse()
            instructionString.matches(REGISTER_PRE) -> StoreRegisterPreOffsetParser(instructionString).parse()
            instructionString.matches(REGISTER_POST) -> LoadRegisterPostOffsetParser(instructionString).parse()
            instructionString.matches(VARIABLE) -> StoreVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
