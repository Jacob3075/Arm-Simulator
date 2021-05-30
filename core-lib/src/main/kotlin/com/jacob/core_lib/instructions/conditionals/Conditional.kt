package com.jacob.core_lib.instructions.conditionals

sealed interface Conditional

enum class Conditionals {
    EQ, NE, AL, CS, CC
}
