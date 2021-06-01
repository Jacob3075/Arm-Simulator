package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.multipy.Multiply
import com.jacob.core_lib.parser.instructions.InstructionString
import com.jacob.core_lib.word.ImmediateValue
import kotlin.reflect.KFunction1

class MultiplyImmediateParser internal constructor(
    private val instructionString: InstructionString,
    private val strategy: KFunction1<String, ImmediateValue>
) : MultiplyInstructionParser {
    override fun parse(): Instruction {
//        MUL R1, R2, #3
        val operands = instructionString.operands

        val destinationRegister = operands.first().toRegisterAddress(::DestinationRegister)
        val sourceRegister = operands[1].toRegisterAddress(::SourceRegister)
        val immediateValue = operands.last().let(strategy)

        return Multiply.of(destinationRegister, sourceRegister, immediateValue, instructionString.conditional)
    }
}
