package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray

class Label(val labelName: String) : Instruction {
    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        // LABELS DO NOT CHANGE OR DO ANYTHING
    }
}
