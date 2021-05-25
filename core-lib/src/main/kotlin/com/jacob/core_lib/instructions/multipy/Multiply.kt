package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.ImmediateValue

interface Multiply : Instruction {

    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            immediateValue: ImmediateValue
        ): Multiply {
            return MultiplyImmediate(destinationRegister, sourceRegister, immediateValue)
        }

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            immediateValue: SourceRegister,
        ): Multiply {
            TODO("Not yet implemented")
        }
    }
}
