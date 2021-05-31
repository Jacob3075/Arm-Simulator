package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Store.VARIABLE
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = when {
            instructionString.mainInstruction.matches(REGISTER_IMMEDIATE_OFFSET) -> StoreRegisterImmediateOffsetParser(
                instructionString
            ).parse()
            instructionString.mainInstruction.matches(REGISTER_PRE) -> StoreRegisterPreOffsetParser(instructionString).parse()
            instructionString.mainInstruction.matches(REGISTER_POST) -> StoreRegisterPostOffsetParser(instructionString).parse()
            instructionString.mainInstruction.matches(VARIABLE) -> StoreVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
