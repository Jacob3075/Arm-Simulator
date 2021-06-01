package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.parser.instructions.InstructionString
import com.jacob.core_lib.word.ImmediateValue

class MoveImmediateParser internal constructor(
    private val instructionString: InstructionString,
    private val strategy: (String) -> ImmediateValue
) : MoveInstructionParser {

    override fun parse(): Instruction {
        val operands = instructionString.operands

        val immediateValue = operands.last().let(strategy)

        val destinationRegister = operands.first()
            .toRegisterAddress(::DestinationRegister)

        return Move.of(destinationRegister, immediateValue)
    }

}
