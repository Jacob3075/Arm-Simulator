@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.word.ImmediateValue

class SubImmediateInstructionParser internal constructor(private val instructionString: String) :
    SubInstructionParser {

    override fun invoke(): Instruction {
        // SUB r4, r4, #5
        val operands = instructionString.removePrefix("SUB")
            .split(",")
            .map(String::trim)

        val immediateValue = operands.last()
            .removePrefix("#")
            .toInt()
            .let(::ImmediateValue)

        val registers: List<RegisterAddress> = operands.take(2)
            .map { it.replace("R", "REGISTER_") }
            .map { RegisterAddress.valueOf(it) }

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Sub.of(destinationRegister, sourceRegister, immediateValue)
    }

}
