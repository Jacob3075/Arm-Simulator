package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.OffsetTypes
import com.jacob.core_lib.instructions.OffsetTypes.IMMEDIATE
import com.jacob.core_lib.word.ImmediateValue

interface Store : Instruction {
    companion object {
        fun of(destinationRegister: MemoryAddress, memoryAddress: SourceRegister) =
            StoreMemoryAddress(destinationRegister, memoryAddress)

        fun of(sourceRegister: SourceRegister, destinationRegister: DestinationRegister) =
            StoreRegisterAddress(sourceRegister, destinationRegister)

        fun of(sourceRegister: SourceRegister, variableName: String) =
            StoreVariableAddress(sourceRegister, variableName)

        fun of(
            sourceRegister: SourceRegister,
            destinationRegister: DestinationRegister,
            offset: ImmediateValue,
            offsetType: OffsetTypes = IMMEDIATE
        ): Store = when (offsetType) {
            IMMEDIATE -> StoreRegisterAddressWithImmediateOffset(destinationRegister, sourceRegister, offset)
            OffsetTypes.POST -> StoreRegisterAddressWithPostOffset(destinationRegister, sourceRegister, offset)
            OffsetTypes.PRE -> StoreRegisterAddressWithPreOffset(destinationRegister, sourceRegister, offset)
        }
    }
}
