package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.load.Load

class LoadRegisterInstructionsParser(private val instructionString: String) :
    LoadInstructionParser {
    override fun invoke(): Instruction {
        val operands = instructionString.removePrefix("LDR")
            .split(",")
            .map(String::trim)

        val destinationRegister = operands.first()
            .toRegisterAddress(::DestinationRegister)

        val sourceRegister = operands.last()
            .replace("[", "")
            .replace("]", "")
            .toRegisterAddress(::SourceRegister)

        return Load.of(destinationRegister, sourceRegister)
    }

}
