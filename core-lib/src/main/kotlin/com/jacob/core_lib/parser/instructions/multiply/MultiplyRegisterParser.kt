package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddresses
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.multipy.Multiply
import com.jacob.core_lib.parser.instructions.InstructionString

class MultiplyRegisterParser internal constructor(private val instructionString: InstructionString) :
    MultiplyInstructionParser {
    override fun parse(): Instruction {
//        MUL R1, R2, R3
        val registers = instructionString.operands.toRegisterAddresses()

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister1 = registers[1].let(::SourceRegister)
        val sourceRegister2 = registers.last().let(::SourceRegister)

        return Multiply.of(destinationRegister, sourceRegister1, sourceRegister2, instructionString.conditional)
    }
}
