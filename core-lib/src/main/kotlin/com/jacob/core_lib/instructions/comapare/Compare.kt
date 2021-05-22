package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Instruction

interface Compare : Instruction {

    companion object {
        fun from(sourceRegister1: SourceRegister, sourceRegister2: SourceRegister): CompareRegister {
            return CompareRegister(sourceRegister1, sourceRegister2)
        }
    }
}
