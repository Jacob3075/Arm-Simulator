package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.conditionals.instructionWithConditional

data class Branch internal constructor(internal val labelName: String) : Instruction {

    companion object {
        fun of(labelName: String, conditional: Conditionals = Conditionals.AL) = instructionWithConditional(
            Branch(labelName),
            conditional
        )
    }

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val label = executionEnvironment.labels.find { it.labelName == labelName }
            ?: throw IllegalArgumentException("Label $labelName Not Found")

        executionEnvironment.registerArray.programCounter.updateProgramCounter(label.index)
    }
}
