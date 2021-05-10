package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.ImmediateValue

interface Sub : Instruction {

    companion object {
        fun of(
                destinationRegister: DestinationRegister,
                sourceRegister1: SourceRegister,
                sourceRegister2: SourceRegister,
        ) = SubRegister(
                destinationRegister,
                sourceRegister1,
                sourceRegister2
        )

        fun of(
                destinationRegister: DestinationRegister,
                sourceRegister1: SourceRegister,
                immediateValue: ImmediateValue
        ) = SubImmediate(
                destinationRegister,
                sourceRegister1,
                immediateValue
        )
    }
}
