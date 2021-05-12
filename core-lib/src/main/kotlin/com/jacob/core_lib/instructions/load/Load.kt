package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.instructions.Instruction

interface Load : Instruction {
    companion object {
        fun of(destinationRegister: DestinationRegister, variableName: String) =
            LoadVariableAddress(destinationRegister, variableName)

        fun of(destinationRegister: DestinationRegister, memoryAddress: MemoryAddress) =
            LoadMemoryAddress(destinationRegister, memoryAddress)
    }
}
