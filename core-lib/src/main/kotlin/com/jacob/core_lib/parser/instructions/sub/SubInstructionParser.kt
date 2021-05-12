package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.InstructionRegex.Sub.Companion.IMMEDIATE
import com.jacob.core_lib.common.InstructionRegex.Sub.Companion.REGISTER
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String): Instruction = when {
            instructionString.matches(REGISTER) -> SubRegisterInstructionParser(instructionString).invoke()
            instructionString.matches(IMMEDIATE) -> SubImmediateInstructionParser(instructionString).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
