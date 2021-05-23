package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Add.Companion.REGISTER
import com.jacob.core_lib.parser.instructions.InstructionParser

interface AddInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(REGISTER) -> AddRegisterInstructionParser(instructionString).parse()
            instructionString.matches(IMMEDIATE_DEC) -> AddImmediateInstructionParser(
                instructionString,
                String::immediateFromDec
            ).parse()
            instructionString.matches(IMMEDIATE_HEX) -> AddImmediateInstructionParser(
                instructionString,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
