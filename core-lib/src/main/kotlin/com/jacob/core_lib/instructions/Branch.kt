package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.ExecutionEnvironment

class Branch(private val labelName: String) : Instruction {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val label = executionEnvironment.labels.find { label -> label.labelName == labelName }
            ?: throw IllegalArgumentException("Label $labelName Not Found")

        executionEnvironment.registerArray.programCounter.updateProgramCounter(label.index)
    }
}
