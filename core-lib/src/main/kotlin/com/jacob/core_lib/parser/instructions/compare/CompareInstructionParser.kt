package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Compare.REGISTER
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface CompareInstructionParser : InstructionParser {
    companion object {
        fun from(instruction: InstructionString) = when {
            instruction.mainInstruction.matches(REGISTER) -> CompareRegisterInstructionParser(instruction).parse()
            instruction.mainInstruction.matches(IMMEDIATE_DEC) -> CompareImmediateInstructionParser(
                instruction,
                String::immediateFromDec
            ).parse()
            instruction.mainInstruction.matches(IMMEDIATE_HEX) -> CompareImmediateInstructionParser(
                instruction,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instruction")
        }
    }
}
