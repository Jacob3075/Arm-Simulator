package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.regex.InstructionRegex.Load.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Load.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Load.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Load.VARIABLE
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface LoadInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER_IMMEDIATE_OFFSET) -> LoadRegisterImmediateOffsetParser(instructionString).parse()
                matches(REGISTER_PRE) -> LoadRegisterPreOffsetParser(instructionString).parse()
                matches(REGISTER_POST) -> LoadRegisterPostOffsetParser(instructionString).parse()
                matches(VARIABLE) -> LoadVariableInstructionParser(instructionString).parse()
                else -> throw IllegalArgumentException("Cannot parse string: $this")
            }
        }
    }
}
