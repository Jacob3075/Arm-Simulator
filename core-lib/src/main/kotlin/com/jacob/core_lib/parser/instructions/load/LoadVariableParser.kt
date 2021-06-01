package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.load.Load
import com.jacob.core_lib.parser.InstructionString

class LoadVariableParser(private val instructionString: InstructionString) : LoadInstructionParser {
    override fun parse(): Instruction {
        val operands = instructionString.operands

        val destinationRegister = operands.first().toRegisterAddress(::DestinationRegister)
        val variableName = operands.last().removePrefix("=")

        return Load.of(destinationRegister, variableName)
    }

}
