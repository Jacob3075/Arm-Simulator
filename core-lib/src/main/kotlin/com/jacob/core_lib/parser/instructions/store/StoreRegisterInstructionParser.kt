package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.store.Store

class StoreRegisterInstructionParser(private val instructionString: String) : StoreInstructionParser {
    override fun invoke(): Instruction {
        // STR R3, [R1]
        val operands = instructionString.removePrefix("STR")
            .split(",")
            .map(String::trim)

        val sourceRegister = operands.first().toRegisterAddress(::SourceRegister)

        val destinationRegister = operands.last()
            .removePrefix("[")
            .removeSuffix("]")
            .toRegisterAddress(::DestinationRegister)

        return Store.of(sourceRegister, destinationRegister)
    }

}
