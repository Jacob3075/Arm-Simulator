package com.jacob.core_lib.parser.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.load.Load

class LoadVariableInstructionParser(private val instructionString: String) :
    LoadInstructionParser {
    override fun invoke(): Instruction {
        // LDR R0,=A
        val operands = instructionString.removePrefix("LDR")
            .split(",")
            .map(String::trim)

        require(operands.size == 2)

        val destinationRegister = operands.first()
            .replace("R", "REGISTER_")
            .let(RegisterAddress::valueOf)
            .let(::DestinationRegister)

        val variableName = operands.last().removePrefix("=")

        return Load.of(destinationRegister, variableName)
    }

}
