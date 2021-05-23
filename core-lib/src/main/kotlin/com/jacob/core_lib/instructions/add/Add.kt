package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.ShiftOperations
import com.jacob.core_lib.word.ImmediateValue

interface Add : Instruction {
    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            sourceRegister2: SourceRegister,
            shiftOperation: ShiftOperations = ShiftOperations.None
        ) = AddRegister(
            destinationRegister,
            sourceRegister1,
            sourceRegister2
        )

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            immediateValue: ImmediateValue,
            shiftOperation: ShiftOperations = ShiftOperations.None
        ) = AddImmediate(
            destinationRegister,
            sourceRegister1,
            immediateValue
        )
    }
}
