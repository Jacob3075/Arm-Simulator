package com.jacob.core_lib.core

data class ExecutionEnvironment(
    val registerArray: RegisterArray,
    val memoryArray: MemoryArray,
    val labels: List<Label>,
    val variables: List<Variable>
)
