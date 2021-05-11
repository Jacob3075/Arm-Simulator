package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.ExecutionEnvironment

class Label(val labelName: String) : Instruction {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        // LABELS DO NOT CHANGE OR DO ANYTHING
    }
}
