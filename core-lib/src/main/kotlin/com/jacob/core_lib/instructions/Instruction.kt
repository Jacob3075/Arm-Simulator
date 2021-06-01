package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.ExecutionEnvironment

interface Instruction {
    fun execute(executionEnvironment: ExecutionEnvironment)
}
