package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.regex.InstructionRegex.Compare.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.Companion.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser

interface CompareInstructionParser : InstructionParser {
    companion object {
        fun from(instruction: String) = when {
            instruction.matches(REGISTER) -> CompareRegisterInstructionParser(instruction).invoke()
            instruction.matches(IMMEDIATE_DEC) -> TODO()
            instruction.matches(IMMEDIATE_HEX) -> TODO()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instruction")
        }
    }
}
