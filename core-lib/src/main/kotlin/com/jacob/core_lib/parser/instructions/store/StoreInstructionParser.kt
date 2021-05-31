package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.regex.InstructionRegex.Store.IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Store.VARIABLE
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(IMMEDIATE_OFFSET) -> StoreRegisterImmediateOffsetParser(instructionString).parse()
                matches(REGISTER_PRE) -> StoreRegisterPreOffsetParser(instructionString).parse()
                matches(REGISTER_POST) -> StoreRegisterPostOffsetParser(instructionString).parse()
                matches(VARIABLE) -> StoreVariableInstructionParser(instructionString).parse()
                else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
            }
        }
    }
}
