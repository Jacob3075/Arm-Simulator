@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.REGISTER
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {

        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(REGISTER) -> SubRegisterInstructionParser(instructionString).parse()
                matches(IMMEDIATE_DEC) -> SubImmediateInstructionParser(
                    instructionString,
                    String::immediateFromDec
                ).parse()
                matches(IMMEDIATE_HEX) -> SubImmediateInstructionParser(
                    instructionString,
                    String::immediateFromHex
                ).parse()
                else -> throw IllegalArgumentException("Cannot parse string: $this")
            }
        }
    }
}
