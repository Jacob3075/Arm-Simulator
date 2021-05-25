package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.word.ImmediateValue

interface Multiply : Instruction {

    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            immediateValue: ImmediateValue
        ): Multiply {
            TODO()
        }

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            immediateValue: SourceRegister,
            shiftOperation: ShiftOperation,
        ): Multiply {
            TODO("Not yet implemented")
        }
    }
}
