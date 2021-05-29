@file:Suppress("DuplicatedCode")

package com.jacob.core_lib.parser.instructions.store

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.toRegisterAddress
import com.jacob.core_lib.instructions.OffsetTypes
import com.jacob.core_lib.instructions.store.Store
import com.jacob.core_lib.parser.InstructionString

class StoreRegisterPostOffsetParser(private val instructionString: InstructionString) : StoreInstructionParser {
    override fun parse(): Store {
//        STR R1. [R2], #3
        val operands = instructionString.operands

        val sourceRegister = operands.first().toRegisterAddress(::SourceRegister)
        val destinationRegister = operands[1].toRegisterAddress(::DestinationRegister)
        val immediateOffset = operands.getOrNull(2)?.immediateFromDec() ?: 0.I

        return Store.of(sourceRegister, destinationRegister, immediateOffset, OffsetTypes.POST)
    }
}
