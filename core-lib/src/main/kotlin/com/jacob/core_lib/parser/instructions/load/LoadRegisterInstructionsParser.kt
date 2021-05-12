package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.load.Load

class LoadRegisterInstructionsParser(private val instructionString: String) :
    LoadInstructionParser {
    override fun invoke(): Instruction {
        // LDR R1, [R0]
        val operands = instructionString.removePrefix("LDR")
            .split(",")
            .map(String::trim)

        require(operands.size == 2)

        val destinationRegister = operands.first()
            .toRegisterAddress(::DestinationRegister)

        val sourceRegister = operands.last()
            .replace("[", "")
            .replace("]", "")
            .toRegisterAddress(::SourceRegister)

        return Load.of(destinationRegister, sourceRegister)
    }

}
