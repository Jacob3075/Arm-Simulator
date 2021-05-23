package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.regex.InstructionRegex.Branch.Companion.LABEL
import com.jacob.core_lib.instructions.Branch

class BranchInstructionParser(private val instructionString: String) : InstructionParser {

    companion object {
        fun from(instructionString: String) = when {
            instructionString.matches(LABEL) -> BranchInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }

    // B LABEL_NAME
    override fun parse() = instructionString.removePrefix("B").trim().let(::Branch)
}
