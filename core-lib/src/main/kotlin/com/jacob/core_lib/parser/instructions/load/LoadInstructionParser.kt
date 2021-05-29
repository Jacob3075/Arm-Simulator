package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.regex.InstructionRegex.Load.Companion.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Load.Companion.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Load.Companion.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Load.Companion.VARIABLE
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface LoadInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = when {
            instructionString.coreInstruction.matches(REGISTER_IMMEDIATE_OFFSET) -> LoadRegisterImmediateOffsetParser(
                instructionString
            ).parse()
            instructionString.coreInstruction.matches(REGISTER_PRE) -> LoadRegisterPreOffsetParser(instructionString).parse()
            instructionString.coreInstruction.matches(REGISTER_POST) -> LoadRegisterPostOffsetParser(instructionString).parse()
            instructionString.coreInstruction.matches(VARIABLE) -> LoadVariableInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
