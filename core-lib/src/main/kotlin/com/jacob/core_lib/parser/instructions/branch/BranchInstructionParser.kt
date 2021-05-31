package com.jacob.core_lib.parser.instructions.branch

import com.jacob.core_lib.common.regex.InstructionRegex.Branch.LABEL
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.parser.InstructionString
import com.jacob.core_lib.parser.instructions.InstructionParser

class BranchInstructionParser(private val instructionString: InstructionString) : InstructionParser {

    companion object {
        fun from(instructionString: InstructionString) = when {
            instructionString.mainInstruction.matches(LABEL) -> BranchInstructionParser(instructionString).parse()
            else -> throw IllegalArgumentException("Cannot parse instruction: $instructionString")
        }
    }

    // B LABEL_NAME
    override fun parse() = instructionString.operands.first().let(::Branch)
}
