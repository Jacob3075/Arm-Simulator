package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.word.ImmediateValue

interface Sub : Instruction {

    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            sourceRegister2: SourceRegister,
            shiftOperation: ShiftOperation = ShiftOperation.None,
            conditional: Conditionals = Conditionals.AL,
        ) = SubRegister(
            destinationRegister,
            sourceRegister1,
            sourceRegister2,
            shiftOperation
        ).withConditional(conditional)

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            immediateValue: ImmediateValue,
            shiftOperation: ShiftOperation = ShiftOperation.None,
            conditional: Conditionals = Conditionals.AL,
        ) = SubImmediate(
            destinationRegister,
            sourceRegister1,
            immediateValue,
            shiftOperation
        ).withConditional(conditional)
    }
}
