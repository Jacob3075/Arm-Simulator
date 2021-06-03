package com.jacob.core_lib.core

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.W

class Core(
    _memoryArray: MemoryArray = MemoryArray(),
    _registerArray: RegisterArray = RegisterArray(),
    private val program: Program,
) {
    var memoryArray: MemoryArray private set
    var registerArray: RegisterArray private set

    init {
        memoryArray = processMemoryArray(_memoryArray)
        registerArray = _registerArray
    }

    private fun processMemoryArray(_memoryArray: MemoryArray): MemoryArray {
        program.parsedData.forEachIndexed { index, parsedData ->
            _memoryArray.setWordAt(index.MA, parsedData.variableValue.W)
        }
        return _memoryArray
    }

    fun runNextInstruction(): Boolean {
        val nextInstruction = program.getInstructionAt(registerArray.programCounter) ?: return false
        val executionEnvironment = ExecutionEnvironment(
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

    fun resetProgram() {
        memoryArray = processMemoryArray(MemoryArray())
        registerArray = RegisterArray()
    }
}
