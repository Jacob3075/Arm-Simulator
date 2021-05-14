package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.regex.InstructionRegex.Companion.LABEL
import com.jacob.core_lib.instructions.Label

class LabelParser(private val instructionString: String) : InstructionParser {
    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(LABEL) -> LabelParser(instructionString).invoke()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }

    // LABEl_NAME:
    override fun invoke() = instructionString.removeSuffix(":").let(::Label)
}
