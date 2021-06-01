package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.conditionals.instructionWithConditional
import com.jacob.core_lib.word.ImmediateValue

interface Compare : Instruction {

    companion object {
        fun from(
            sourceRegister1: SourceRegister,
            sourceRegister2: SourceRegister,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            CompareRegister(sourceRegister1, sourceRegister2),
            conditional
        )

        fun from(
            sourceRegister: SourceRegister,
            immediateValue: ImmediateValue,
            conditional: Conditionals = Conditionals.AL
        ) = instructionWithConditional(
            CompareImmediate(sourceRegister, immediateValue),
            conditional
        )
    }
}
