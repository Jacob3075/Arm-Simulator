package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.conditionals.Conditionals.*
import com.jacob.core_lib.instructions.conditionals.Equal

interface Instruction {
    fun execute(executionEnvironment: ExecutionEnvironment)
    fun withConditional(conditional: Conditionals): Instruction = when (conditional) {
        AL -> Always(this)
        EQ -> Equal(this)
        NE -> TODO()
        CS -> TODO()
        CC -> TODO()
    }
}
