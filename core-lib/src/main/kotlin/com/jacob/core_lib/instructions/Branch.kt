package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray

class Branch(val labelName: String) : Instruction {
    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        val label = labels.find { label -> label.labelName == labelName }
            ?: throw IllegalArgumentException("Label $labelName Not Found")

        registerArray.programCounter.updateProgramCounter(label.index + 1)
    }
}
