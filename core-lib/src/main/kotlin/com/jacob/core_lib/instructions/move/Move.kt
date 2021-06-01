package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.conditionals.instructionWithConditional
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.word.ImmediateValue

interface Move : Instruction {

    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            immediateValue: ImmediateValue,
            shiftOperation: ShiftOperation = ShiftOperation,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            MoveImmediate(destinationRegister, immediateValue, shiftOperation),
            conditional
        )

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            shiftOperation: ShiftOperation = ShiftOperation,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            MoveRegister(destinationRegister, sourceRegister, shiftOperation),
            conditional
        )
    }
}
