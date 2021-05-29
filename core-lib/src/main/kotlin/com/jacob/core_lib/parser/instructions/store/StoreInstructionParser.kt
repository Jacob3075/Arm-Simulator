package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Store.Companion.VARIABLE
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = when {
            instructionString.coreInstruction.matches(REGISTER_IMMEDIATE_OFFSET) -> StoreRegisterImmediateOffsetParser(
                instructionString
            ).parse()
            instructionString.coreInstruction.matches(REGISTER_PRE) -> StoreRegisterPreOffsetParser(instructionString).parse()
            instructionString.coreInstruction.matches(REGISTER_POST) -> StoreRegisterPostOffsetParser(instructionString).parse()
            instructionString.coreInstruction.matches(VARIABLE) -> StoreVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
