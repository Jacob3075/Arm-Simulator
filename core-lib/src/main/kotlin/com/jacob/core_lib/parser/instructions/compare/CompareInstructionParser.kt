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
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> CompareRegisterInstructionParser(instructionString).parse()
                matches(IMMEDIATE_DEC) -> CompareImmediateInstructionParser(
                    instructionString,
                    String::immediateFromDec
                ).parse()
                matches(IMMEDIATE_HEX) -> CompareImmediateInstructionParser(
                    instructionString,
                    String::immediateFromHex
                ).parse()
                else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
            }
        }
    }
}
