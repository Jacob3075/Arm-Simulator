package com.jacob.core_lib.instructions.conditionals

import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction

class PositiveOrZero(override val instruction: Instruction) : Conditional, Instruction by instruction {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        if (!executionEnvironment.registerArray.statusRegister.negative) {
            instruction.execute(executionEnvironment)
        }
    }
}
