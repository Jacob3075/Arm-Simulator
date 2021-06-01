@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.parser.InstructionString

class AddRegisterParser internal constructor(private val instructionString: InstructionString) :
    AddInstructionParser {

    override fun parse(): Instruction {
        val registers = instructionString.operands.toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister1 = registers[1].let(::SourceRegister)
        val sourceRegister2 = registers.last().let(::SourceRegister)

        return Add.of(
            destinationRegister = destinationRegister,
            sourceRegister1 = sourceRegister1,
            sourceRegister2 = sourceRegister2,
            shiftOperation = instructionString.shiftOperation,
            conditional = instructionString.conditional
        )
    }
}
