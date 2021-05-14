package com.jacob.core_lib.core

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.W

class Core(
    val memoryArray: MemoryArray = MemoryArray(),
    val registerArray: RegisterArray = RegisterArray(),
    private val program: Program,
) {
    init {
        program.parsedData.forEachIndexed { index, parsedData ->
            memoryArray.setWordAt(index.MA, parsedData.variableValue.W)
        }
    }

    fun runProgram() {
        while (program.hasMoreInstructions(registerArray.programCounter)) {
            val nextInstruction = program.getInstructionAt(registerArray.programCounter) ?: return
            val executionEnvironment =
                ExecutionEnvironment(
                    registerArray = registerArray,
                    memoryArray = memoryArray,
                    labels = program.labels,
                    variables = program.variables
                )

            nextInstruction.execute(executionEnvironment)
        }
    }
}
