package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.comapare.Compare
import com.jacob.core_lib.instructions.comapare.CompareRegister

class CompareRegisterInstructionParser(private val instruction: String) : CompareInstructionParser {
    override fun parse(): CompareRegister {
        // CMP R1, R2
        val registers = instruction.removePrefix("CMP")
            .split(",")
            .map(String::trim)
            .toRegisterAddresses()
            .map(::SourceRegister)

        return Compare.from(registers.first(), registers.last())
    }
}
