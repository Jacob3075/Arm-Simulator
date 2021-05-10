package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.ImmediateValue

interface Move : Instruction {

    companion object {
        fun of(destinationRegister: DestinationRegister, immediateValue: ImmediateValue): Instruction =
                MoveImmediate(destinationRegister, immediateValue)

        fun of(destinationRegister: DestinationRegister, sourceRegister: SourceRegister): Instruction =
                MoveRegister(destinationRegister, sourceRegister)
    }
}
