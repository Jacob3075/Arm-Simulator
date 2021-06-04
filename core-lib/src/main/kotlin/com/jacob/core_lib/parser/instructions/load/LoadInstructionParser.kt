package com.jacob.core_lib.parser.instructions.load

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.regex.InstructionRegex.Load.REGISTER_IMMEDIATE_OFFSET
import com.jacob.core_lib.common.regex.InstructionRegex.Load.REGISTER_POST
import com.jacob.core_lib.common.regex.InstructionRegex.Load.REGISTER_PRE
import com.jacob.core_lib.common.regex.InstructionRegex.Load.VARIABLE
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

interface LoadInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER_IMMEDIATE_OFFSET) -> LoadRegisterOffsetParser(instructionString).parse().valid()
                matches(REGISTER_PRE) -> LoadRegisterPreOffsetParser(instructionString).parse().valid()
                matches(REGISTER_POST) -> LoadRegisterPostOffsetParser(instructionString).parse().valid()
                matches(VARIABLE) -> LoadVariableParser(instructionString).parse().valid()
                else -> InvalidInstruction("Cannot parse string: $this").invalidNel()
            }
        }
    }
}
