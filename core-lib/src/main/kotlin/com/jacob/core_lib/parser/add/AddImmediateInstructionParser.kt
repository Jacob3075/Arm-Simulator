package com.jacob.core_lib.parser.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.word.ImmediateValue

class AddImmediateInstructionParser(private val instructionString: String) : AddInstructionParser {

    override fun invoke(): Instruction {
        // ADD r4, r4, #5
        val operands = instructionString.dropWhile { it != ' ' }
            .split(",")
            .map(String::trim)
            .map(String::uppercase)

        val immediateInt = operands.last().removePrefix("#").toInt()
        val immediateValue = ImmediateValue(immediateInt)

        val registers: List<RegisterAddress> = operands.take(2)
            .map { it.replace("R", "REGISTER_") }
            .map { RegisterAddress.valueOf(it) }

        val destinationRegisterAddress = registers.first()
        val sourceAddress = registers.last()

        val destinationRegister = DestinationRegister(destinationRegisterAddress)
        val sourceRegister = SourceRegister(sourceAddress)

        return Add.of(destinationRegister, sourceRegister, immediateValue)
    }
}
