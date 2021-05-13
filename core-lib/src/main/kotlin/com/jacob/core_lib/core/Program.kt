package com.jacob.core_lib.core

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.registers.ProgramCounter
import com.jacob.core_lib.instructions.Label as InstructionsLabel

class Program(private val instructions: List<Instruction>, val variables: List<Variable>) {

    private val _labels: ArrayList<Label> = ArrayList()
    val labels: List<Label>
        get() = _labels

    init {
        instructions.forEachIndexed { index, instruction ->
            if (instruction is InstructionsLabel) {
                _labels.add(Label(instruction.labelName, index))
            }
        }
    }

    fun getInstructionAt(programCounter: ProgramCounter): Instruction? {
        val instruction: Instruction? = instructions.getOrNull(programCounter.nextInstructionAddress)
        programCounter.nextInstruction()
        return instruction
    }

    fun hasMoreInstructions(programCounter: ProgramCounter): Boolean {
        return programCounter.nextInstructionAddress < instructions.size
    }

}
