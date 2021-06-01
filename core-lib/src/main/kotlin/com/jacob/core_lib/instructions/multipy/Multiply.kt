package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.conditionals.instructionWithConditional
import com.jacob.core_lib.word.ImmediateValue

interface Multiply : Instruction {

    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister: SourceRegister,
            immediateValue: ImmediateValue,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            MultiplyImmediate(destinationRegister, sourceRegister, immediateValue),
            conditional
        )

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            sourceRegister2: SourceRegister,
            conditional: Conditionals = Conditionals.AL,
        ) = instructionWithConditional(
            MultiplyRegister(destinationRegister, sourceRegister1, sourceRegister2),
            conditional
        )
    }
}
