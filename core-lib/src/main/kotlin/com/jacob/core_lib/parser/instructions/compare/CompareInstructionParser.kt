package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.Companion.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser

interface CompareInstructionParser : InstructionParser {
    companion object {
        fun from(instruction: String) = when {
            instruction.matches(REGISTER) -> CompareRegisterInstructionParser(instruction).invoke()
            instruction.matches(IMMEDIATE_DEC) -> CompareImmediateInstructionParser(
                instruction,
                String::immediateFromDec
            ).invoke()
            instruction.matches(IMMEDIATE_HEX) -> CompareImmediateInstructionParser(
                instruction,
                String::immediateFromHex
            ).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instruction")
        }
    }
}
