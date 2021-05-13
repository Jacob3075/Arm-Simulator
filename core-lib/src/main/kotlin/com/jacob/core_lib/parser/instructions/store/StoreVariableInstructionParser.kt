package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.store.Store

class StoreVariableInstructionParser(private val instructionString: String) : StoreInstructionParser {
    override fun invoke(): Instruction {
        // STR R2, =STR
        val operands = instructionString.removePrefix("STR")
            .split(",")
            .map(String::trim)

        val sourceRegister = operands.first()
            .toRegisterAddress(::SourceRegister)

        val variableName = operands.last()
            .removePrefix("=")

        return Store.of(sourceRegister, variableName)
    }

}
