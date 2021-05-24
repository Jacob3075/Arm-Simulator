package com.jacob.core_lib.parser.instructions.label

import com.jacob.core_lib.common.regex.InstructionRegex.Companion.LABEL
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.parser.instructions.InstructionParser

class LabelParser(private val instructionString: String) : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(LABEL) -> LabelParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }

    // LABEl_NAME:
    override fun parse() = instructionString.removeSuffix(":").let(::Label)
}
