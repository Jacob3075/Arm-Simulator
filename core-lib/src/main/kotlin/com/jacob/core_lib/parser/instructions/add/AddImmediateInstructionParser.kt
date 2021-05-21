@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.word.ImmediateValue

class AddImmediateInstructionParser internal constructor(
    private val instructionString: String,
    private val strategy: (String) -> ImmediateValue
) : AddInstructionParser {

    override fun invoke(): Instruction {
        val operands = instructionString.removePrefix("ADD")
            .split(",")
            .map(String::trim)

        val immediateValue = operands.last().let(strategy)

        val registers: List<RegisterAddress> = operands.take(2)
            .toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Add.of(destinationRegister, sourceRegister, immediateValue)
    }
}
