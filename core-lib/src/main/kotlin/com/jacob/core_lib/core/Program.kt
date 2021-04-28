package com.jacob.core_lib.core

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.Move

class Program(private val instructions: List<Instruction>) {

    private val _labels: ArrayList<Label> = ArrayList()
    val labels: List<Label> = _labels

    init {
        instructions.forEachIndexed { index, instruction ->
            if (instruction is Move) {
                _labels.add(Label(object : Any() {}, index))
            }
        }
    }

    fun run(memoryArray: MemoryArray, registerArray: RegisterArray): Unit {
        instructions.forEach { instruction ->
            instruction.execute(memoryArray, registerArray)
        }
    }

}
