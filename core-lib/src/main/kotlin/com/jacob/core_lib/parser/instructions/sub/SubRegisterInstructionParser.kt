package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.instructions.sub.Sub

class SubRegisterInstructionParser internal constructor(
    private val instructionString: String,
    private val shiftOperation: ShiftOperation
) : SubInstructionParser {

    override fun parse(): Instruction {
        val registers: List<RegisterAddresses> = instructionString.removePrefix("SUB")
            .split(",")
            .map(String::trim)
            .toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister1 = registers[1].let(::SourceRegister)
        val sourceRegister2 = registers.last().let(::SourceRegister)

        return Sub.of(destinationRegister, sourceRegister1, sourceRegister2, shiftOperation)
    }

}
