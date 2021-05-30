package com.jacob.core_lib.instructions.conditionals

import com.jacob.core_lib.instructions.Instruction

sealed interface Conditional {
    val instruction: Instruction
}

enum class Conditionals {
    EQ, NE, AL, CS, CC
}
