package com.jacob.core_lib.parser

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.word.ImmediateValue

class AddInstructionParser(private val instructionString: String) : InstructionParser {

    override fun invoke(): Instruction {
        val operands = instructionString.dropWhile { it != ' ' }
            .split(",")
            .map(String::trim)
            .map(String::uppercase)

        return if (instructionString.contains('#')) {
            addImmediateInstruction(operands)
        } else {
            addRegisterInstruction(operands)
        }
    }

    private fun addRegisterInstruction(operands: List<String>): AddRegister {
        // ADD r4, r4, r5
        val registers: List<RegisterAddress> = operands.map { it.replace("R", "REGISTER_") }
            .map { RegisterAddress.valueOf(it) }

        val destinationRegisterAddress = registers.first()
        val sourceAddress1 = registers[1]
        val sourceAddress2 = registers.last()

        val destinationRegister = DestinationRegister(destinationRegisterAddress)
        val sourceRegister1 = SourceRegister(sourceAddress1)
        val sourceRegister2 = SourceRegister(sourceAddress2)

        return Add.of(destinationRegister, sourceRegister1, sourceRegister2)
    }

    private fun addImmediateInstruction(operands: List<String>): AddImmediate {
        // ADD r4, r4, #5
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
