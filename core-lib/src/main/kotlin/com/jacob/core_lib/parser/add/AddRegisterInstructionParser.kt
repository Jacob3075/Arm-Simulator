package com.jacob.core_lib.parser.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add

class AddRegisterInstructionParser internal constructor(private val instructionString: String) : AddInstructionParser {

    override fun invoke(): Instruction {
        // ADD r4, r4, r5
        val registers: List<RegisterAddress> = instructionString.dropWhile { it != ' ' }
            .split(",")
            .map(String::trim)
            .map(String::uppercase).map { it.replace("R", "REGISTER_") }
            .map { RegisterAddress.valueOf(it) }

        val destinationRegisterAddress = registers.first()
        val sourceAddress1 = registers[1]
        val sourceAddress2 = registers.last()

        val destinationRegister = DestinationRegister(destinationRegisterAddress)
        val sourceRegister1 = SourceRegister(sourceAddress1)
        val sourceRegister2 = SourceRegister(sourceAddress2)

        return Add.of(destinationRegister, sourceRegister1, sourceRegister2)
    }

}
