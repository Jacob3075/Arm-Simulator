package com.jacob.core_lib.parser.instructions.label

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.regex.InstructionRegex.LABEL
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

class LabelParser(private val instructionString: InstructionString) : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(LABEL) -> LabelParser(instructionString).parse().valid()
                else -> InvalidInstruction("Cannot parse instruction: $instructionString").invalidNel()
            }
        }
    }

    // LABEl_NAME:
    override fun parse() = instructionString.mainInstruction.removeSuffix(":").let(::Label)
}
