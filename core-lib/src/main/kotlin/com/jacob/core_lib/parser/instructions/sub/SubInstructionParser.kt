package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.REGISTER
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {
        fun from(instructionString: String): Instruction = when {
            instructionString.matches(REGISTER) -> SubRegisterInstructionParser(instructionString).invoke()
            instructionString.matches(IMMEDIATE_DEC) -> SubImmediateInstructionParser(
                instructionString,
                String::immediateFromDec
            ).invoke()
            instructionString.matches(IMMEDIATE_HEX) -> SubImmediateInstructionParser(
                instructionString,
                String::immediateFromHex
            ).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }
}
