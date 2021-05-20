package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.sub.Sub

class SubHexImmediateInstructionParser(private val instructionString: String) : SubInstructionParser {

    override fun invoke(): Instruction {
        val operands = instructionString.removePrefix("SUB")
            .split(",")
            .map(String::trim)

        val immediateValue = operands.last().immediateFromHex()

        val registers: List<RegisterAddress> = operands.take(2)
            .toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Sub.of(destinationRegister, sourceRegister, immediateValue)
    }
}
