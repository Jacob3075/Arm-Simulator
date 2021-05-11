@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.word.ImmediateValue

class AddImmediateInstructionParser internal constructor(private val instructionString: String) : AddInstructionParser {

    override fun invoke(): Instruction {
        // ADD r4, r4, #5
        val operands = instructionString.removePrefix("ADD")
            .split(",")
            .map(String::trim)

        val immediateValue = operands.last()
            .removePrefix("#")
            .toInt()
            .let(::ImmediateValue)

        val registers: List<RegisterAddress> = operands.take(2)
            .map { it.replace("R", "REGISTER_") }
            .map(RegisterAddress::valueOf)

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Add.of(destinationRegister, sourceRegister, immediateValue)
    }
}
