package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray

interface Instruction {
    fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>)
}
