package com.jacob.core_lib.core

import com.jacob.core_lib.instruction.Instruction
import com.jacob.core_lib.instruction.Move

class Program(instructions: List<Instruction>) {

    private val _labels: ArrayList<Label> = ArrayList()
    val labels: List<Label> = _labels

    init {
        instructions.forEachIndexed { index, instruction ->
            if (instruction is Move) {
                _labels.add(Label(object : Any() {}, index))
            }
        }
    }

}
