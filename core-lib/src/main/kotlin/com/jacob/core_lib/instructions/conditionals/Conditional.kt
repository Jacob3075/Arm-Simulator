package com.jacob.core_lib.instructions.conditionals

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.conditionals.Conditionals.*

sealed interface Conditional {
    val instruction: Instruction
}

fun instructionWithConditional(instruction: Instruction, conditional: Conditionals): Instruction = when (conditional) {
    AL -> Always(instruction)
    EQ -> Equal(instruction)
    NE -> TODO()
    CS -> TODO()
    CC -> TODO()
}

enum class Conditionals {
    EQ, NE, AL, CS, CC
}
