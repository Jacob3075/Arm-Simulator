package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.comapare.Compare
import com.jacob.core_lib.parser.instructions.InstructionString
import com.jacob.core_lib.word.ImmediateValue

class CompareImmediateParser(
    private val instruction: InstructionString,
    private val strategy: (String) -> ImmediateValue
) : CompareInstructionParser {
    override fun parse(): Instruction {
//        CMP R1, #10
        val operands = instruction.operands

        val sourceRegister = operands.first().toRegisterAddress(::SourceRegister)
        val immediateValue = operands.last().let(strategy)

        return Compare.from(sourceRegister, immediateValue, instruction.conditional)
    }
}
