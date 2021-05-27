package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.OffsetTypes
import com.jacob.core_lib.instructions.OffsetTypes.*
import com.jacob.core_lib.word.ImmediateValue

interface Load : Instruction {
    companion object {
        fun of(destinationRegister: DestinationRegister, variableName: String) =
            LoadVariableAddress(destinationRegister, variableName)

        fun of(destinationRegister: DestinationRegister, memoryAddress: MemoryAddress) =
            LoadMemoryAddress(destinationRegister, memoryAddress)

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            offset: ImmediateValue = 0.I,
            offsetType: OffsetTypes = IMMEDIATE
        ): Load = when (offsetType) {
            IMMEDIATE -> LoadRegisterAddressWithImmediateOffset(destinationRegister, sourceRegister, offset)
            POST -> LoadRegisterAddressWithPostOffset(destinationRegister, sourceRegister, offset)
            PRE -> LoadRegisterAddressWithPreOffset(destinationRegister, sourceRegister, offset)
        }
    }
}
