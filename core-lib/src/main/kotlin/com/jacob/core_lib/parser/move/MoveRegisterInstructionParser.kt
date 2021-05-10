package com.jacob.core_lib.parser.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.parser.InstructionParser

class MoveRegisterInstructionParser internal constructor(private val instructionString: String) : InstructionParser {
    override fun invoke(): Instruction {
        // MOV r1, r5
        val registers = instructionString.removePrefix("MOV")
                .split(",")
                .map(String::trim)
                .map { it.replace("R", "REGISTER_") }
                .map(RegisterAddress::valueOf)

        val destinationRegister = registers.first().let(::DestinationRegister)
        val sourceRegister = registers.last().let(::SourceRegister)

        return Move.of(destinationRegister, sourceRegister)
    }

}
