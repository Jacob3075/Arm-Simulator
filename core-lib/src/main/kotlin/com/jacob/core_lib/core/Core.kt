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

    fun runNextInstruction(): Boolean {
        val nextInstruction = program.getInstructionAt(registerArray.programCounter) ?: return false
        val executionEnvironment =
            ExecutionEnvironment(
                registerArray = registerArray,
                memoryArray = memoryArray,
                labels = program.labels,
                variables = program.variables
            )

        nextInstruction.execute(executionEnvironment)

        return true
    }

    fun runProgram() {
        while (program.hasMoreInstructions(registerArray.programCounter)) {
            runNextInstruction()
        }
    }

    fun resetProgramCounter() = registerArray.programCounter.updateProgramCounter(0)
}
