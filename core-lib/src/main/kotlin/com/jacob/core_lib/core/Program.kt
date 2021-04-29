package com.jacob.core_lib.core

import com.jacob.core_lib.instructions.Instruction

@Suppress("RedundantEmptyInitializerBlock")
class Program(private val instructions: List<Instruction>) {

    private val _labels: ArrayList<Label> = ArrayList()
    val labels: List<Label> = _labels

    init {
//        instructions.forEachIndexed { index, instruction ->
//            if (instruction is Move) {
//                _labels.add(Label(object : Any() {}, index))
//            }
//        }
    }

    fun run(memoryArray: MemoryArray, registerArray: RegisterArray) {
        instructions.forEach { instruction ->
            instruction.execute(memoryArray, registerArray)
        }
    }

}
