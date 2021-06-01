package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.comapare.Compare
import com.jacob.core_lib.parser.instructions.InstructionString

class CompareRegisterParser internal constructor(private val instruction: InstructionString) :
    CompareInstructionParser {
    override fun parse(): Instruction {
        // CMP R1, R2
        val registers = instruction.operands.toRegisterAddresses().map(::SourceRegister)

        return Compare.from(registers.first(), registers.last(), instruction.conditional)
    }
}
