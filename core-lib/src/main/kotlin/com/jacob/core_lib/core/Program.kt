package com.jacob.core_lib.core

import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Instruction

class Program(private val instructions: List<Instruction>) {

    private val labels: ArrayList<Label> = ArrayList()

    init {
        instructions.forEachIndexed { index, instruction ->
            if (instruction is Branch) {
                labels.add(Label(instruction.labelName, index))
            }
        }
    }

    fun run(memoryArray: MemoryArray, registerArray: RegisterArray) {
        instructions.forEach { instruction ->
            instruction.execute(memoryArray, registerArray, labels)
        }
    }

}
