package com.jacob.core_lib.parser.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.sub.Sub

class SubRegisterInstructionParser internal constructor(private val instructionString: String) :
    SubInstructionParser {

    override fun invoke(): Instruction {
        // SUB r4, r4, r5
        val registers: List<RegisterAddress> = instructionString.removePrefix("SUB")
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

        return Sub.of(destinationRegister, sourceRegister1, sourceRegister2)
    }

}
