@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Add.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Add.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Add.REGISTER
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface AddInstructionParser : InstructionParser {

    companion object {
        fun from(instructionString: InstructionString) = when {
            instructionString.mainInstruction.matches(REGISTER) -> AddRegisterInstructionParser(
                instructionString,
                instructionString.shiftOperation
            ).parse()
            instructionString.mainInstruction.matches(IMMEDIATE_DEC) -> AddImmediateInstructionParser(
                instructionString,
                instructionString.shiftOperation,
                String::immediateFromDec
            ).parse()
            instructionString.mainInstruction.matches(IMMEDIATE_HEX) -> AddImmediateInstructionParser(
                instructionString,
                instructionString.shiftOperation,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
