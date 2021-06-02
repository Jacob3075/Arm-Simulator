package com.jacob.core_lib.instructions.conditionals

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.conditionals.Conditionals.*

sealed interface Conditional {
    val instruction: Instruction
}

enum class Conditionals {
    EQ, NE, AL, CS, CC
}

fun instructionWithConditional(instruction: Instruction, conditional: Conditionals): Instruction = when (conditional) {
    AL -> Always(instruction)
    EQ -> Equal(instruction)
    NE -> NotEqual(instruction)
    CS -> CarrySet(instruction)
    CC -> CarryClear(instruction)
}
