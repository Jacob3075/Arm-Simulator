package com.jacob.core_lib.parser.instructions.store

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.regex.InstructionRegex.Store.IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Store.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Store.VARIABLE
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface StoreInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(IMMEDIATE_OFFSET) -> StoreRegisterOffsetParser(instructionString).parse().valid()
                matches(REGISTER_PRE) -> StoreRegisterPreOffsetParser(instructionString).parse().valid()
                matches(REGISTER_POST) -> StoreRegisterPostOffsetParser(instructionString).parse().valid()
                matches(VARIABLE) -> StoreVariableParser(instructionString).parse().valid()
                else -> InvalidInstruction("Cannot parse instruction: $instructionString").invalidNel()
            }
        }
    }
}
