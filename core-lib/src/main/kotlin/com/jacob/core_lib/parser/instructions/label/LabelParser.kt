package com.jacob.core_lib.parser.instructions.label

import com.jacob.core_lib.common.regex.InstructionRegex.LABEL
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

class LabelParser(private val instructionString: InstructionString) : InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = with(instructionString.mainInstruction) {
            when {
                matches(LABEL) -> LabelParser(instructionString).parse()
                else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
            }
        }
    }

    // LABEl_NAME:
    override fun parse() = instructionString.mainInstruction.removeSuffix(":").let(::Label)
}
