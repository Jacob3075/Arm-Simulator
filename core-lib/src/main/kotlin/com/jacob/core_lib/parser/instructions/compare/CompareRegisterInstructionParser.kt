package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.comapare.Compare

class CompareRegisterInstructionParser(private val instruction: String) : CompareInstructionParser {
    override fun invoke(): Instruction {
        // CMP R1, R2
        val registers = instruction.removePrefix("CMP")
            .split(",")
            .map(String::trim)
            .toRegisterAddresses()
            .map(::SourceRegister)

        return Compare.from(registers.first(), registers.last())
    }
}
