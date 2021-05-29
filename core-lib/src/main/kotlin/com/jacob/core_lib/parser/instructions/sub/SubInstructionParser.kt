@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.IMMEDIATE_DEC
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.IMMEDIATE_HEX
import com.jacob.core_lib.common.regex.InstructionRegex.Sub.Companion.REGISTER
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

interface SubInstructionParser : InstructionParser {
    companion object {

        fun from(instructionString: InstructionString) = when {
            instructionString.mainInstruction.matches(REGISTER) -> SubRegisterInstructionParser(
                instructionString,
                instructionString.shiftOperation
            ).parse()
            instructionString.mainInstruction.matches(IMMEDIATE_DEC) -> SubImmediateInstructionParser(
                instructionString,
                instructionString.shiftOperation,
                String::immediateFromDec
            ).parse()
            instructionString.mainInstruction.matches(IMMEDIATE_HEX) -> SubImmediateInstructionParser(
                instructionString,
                instructionString.shiftOperation,
                String::immediateFromHex
            ).parse()
            else -> throw IllegalArgumentException("Cannot parse string: $instructionString")
        }
    }
}
