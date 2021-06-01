package com.jacob.core_lib.instructions.load

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

interface Load : Instruction {
    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            variableName: String,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            LoadVariableAddress(destinationRegister, variableName),
            conditional
        )

        fun of(
            destinationRegister: DestinationRegister,
            memoryAddress: MemoryAddress,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            LoadMemoryAddress(destinationRegister, memoryAddress),
            conditional
        )

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            offset: ImmediateValue = 0.I,
            offsetType: OffsetTypes = IMMEDIATE,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            when (offsetType) {
                IMMEDIATE -> LoadRegisterAddressWithImmediateOffset(destinationRegister, sourceRegister, offset)
                POST -> LoadRegisterAddressWithPostOffset(destinationRegister, sourceRegister, offset)
                PRE -> LoadRegisterAddressWithPreOffset(destinationRegister, sourceRegister, offset)
            },
            conditional
        )
    }
}
