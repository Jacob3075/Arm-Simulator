package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add

class AddRegisterInstructionParser internal constructor(private val instructionString: String) : AddInstructionParser {

    override fun parse(): Instruction {
        val registers: List<RegisterAddress> = instructionString.dropWhile { it != ' ' }
            .split(",")
            .map(String::trim)
            .toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister1 = registers[1].let(::SourceRegister)
        val sourceRegister2 = registers.last().let(::SourceRegister)

        return Add.of(destinationRegister, sourceRegister1, sourceRegister2)
    }

}
