package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.ImmediateValue

interface Compare : Instruction {

    companion object {
        fun from(sourceRegister1: SourceRegister, sourceRegister2: SourceRegister) =
            CompareRegister(sourceRegister1, sourceRegister2)

        fun from(sourceRegister: SourceRegister, immediateValue: ImmediateValue) =
            CompareImmediate(sourceRegister, immediateValue)
    }
}
