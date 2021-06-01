package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.OffsetTypes
import com.jacob.core_lib.instructions.OffsetTypes.*
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.conditionals.instructionWithConditional
import com.jacob.core_lib.word.ImmediateValue

interface Store : Instruction {
    companion object {
        fun of(
            destinationRegister: MemoryAddress,
            memoryAddress: SourceRegister,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            StoreMemoryAddress(destinationRegister, memoryAddress),
            conditional
        )

        fun of(
            sourceRegister: SourceRegister,
            variableName: String,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            StoreVariableAddress(sourceRegister, variableName),
            conditional
        )

        fun of(
            sourceRegister: SourceRegister,
            destinationRegister: DestinationRegister,
            offset: ImmediateValue = 0.I,
            offsetType: OffsetTypes = IMMEDIATE,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            when (offsetType) {
                IMMEDIATE -> StoreRegisterAddressWithImmediateOffset(sourceRegister, destinationRegister, offset)
                POST -> StoreRegisterAddressWithPostOffset(sourceRegister, destinationRegister, offset)
                PRE -> StoreRegisterAddressWithPreOffset(sourceRegister, destinationRegister, offset)
            },
            conditional
        )
    }
}
