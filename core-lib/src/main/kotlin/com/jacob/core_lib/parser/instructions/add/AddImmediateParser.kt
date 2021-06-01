package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.parser.instructions.InstructionString
import com.jacob.core_lib.word.ImmediateValue

class AddImmediateParser internal constructor(
    private val instructionString: InstructionString,
    private val strategy: (String) -> ImmediateValue
) : AddInstructionParser {
    override fun parse(): Instruction {
        val operands = instructionString.operands

        val immediateValue = operands.last().let(strategy)

        val registers = operands.take(2).toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Add.of(
            destinationRegister = destinationRegister,
            sourceRegister1 = sourceRegister,
            immediateValue = immediateValue,
            shiftOperation = instructionString.shiftOperation,
            instructionString.conditional
        )
    }
}
