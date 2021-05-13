package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction

interface Store : Instruction {
    companion object {
        fun of(destinationRegister: MemoryAddress, memoryAddress: SourceRegister) =
            StoreMemoryAddress(destinationRegister, memoryAddress)

        fun of(sourceRegister: SourceRegister, destinationRegister: DestinationRegister): StoreRegisterAddress {
            return StoreRegisterAddress(sourceRegister, destinationRegister)
        }

    }
}
