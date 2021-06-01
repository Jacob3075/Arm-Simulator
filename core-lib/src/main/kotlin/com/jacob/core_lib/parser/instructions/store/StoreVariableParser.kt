package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.store.Store
import com.jacob.core_lib.parser.InstructionString

class StoreVariableParser(private val instructionString: InstructionString) : StoreInstructionParser {
    override fun parse(): Instruction {
        // STR R2, =STR
        val operands = instructionString.operands

        val sourceRegister = operands.first().toRegisterAddress(::SourceRegister)
        val variableName = operands.last().removePrefix("=")

        return Store.of(sourceRegister, variableName)
    }

}
