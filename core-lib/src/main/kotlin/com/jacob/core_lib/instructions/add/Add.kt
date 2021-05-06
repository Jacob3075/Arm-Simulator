package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.SourceRegister
import com.jacob.core_lib.word.ImmediateValue

interface Add : Instruction {
    companion object {
        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            sourceRegister2: SourceRegister,
        ): AddRegister {
            return AddRegister(
                destinationRegister,
                sourceRegister1,
                sourceRegister2
            )
        }

        fun of(
            destinationRegister: DestinationRegister,
            sourceRegister1: SourceRegister,
            immediateValue: ImmediateValue
        ): Add {
            return AddImmediate(
                destinationRegister,
                sourceRegister1,
                immediateValue
            )
        }
    }
}
