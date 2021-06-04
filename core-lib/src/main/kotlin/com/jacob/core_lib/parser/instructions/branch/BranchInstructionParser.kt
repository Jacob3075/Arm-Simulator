package com.jacob.core_lib.parser.instructions.branch

import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidInstruction
import com.jacob.core_lib.common.regex.InstructionRegex.Branch.LABEL
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.parser.instructions.InstructionString

class BranchInstructionParser internal constructor(private val instructionString: InstructionString) :
    InstructionParser {
    companion object {
        fun from(instructionString: InstructionString) = when {
            instructionString.mainInstruction.matches(LABEL) -> BranchInstructionParser(instructionString).parse()
                .valid()
            else -> InvalidInstruction("Cannot parse instruction: $instructionString").invalidNel()
        }
    }

    // B LABEL_NAME
    override fun parse() = instructionString.operands.first().let {
        Branch.of(it, instructionString.conditional)
    }
}
