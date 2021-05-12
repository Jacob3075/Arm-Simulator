package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.parser.instructions.InstructionParser
import com.jacob.core_lib.word.ImmediateValue

class MoveImmediateInstructionParser internal constructor(private val instructionString: String) : InstructionParser {

    override fun invoke(): Instruction {
        // MOV r1, #5
        val operands = instructionString.removePrefix("MOV")
            .split(",")
            .map(String::trim)

        val immediateValue = operands.last()
            .removePrefix("#")
            .toInt()
            .let(::ImmediateValue)

        val destinationRegister = operands.first()
            .toRegisterAddress(::DestinationRegister)

        return Move.of(destinationRegister, immediateValue)
    }

}
