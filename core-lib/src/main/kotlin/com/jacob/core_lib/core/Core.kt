package com.jacob.core_lib.core

class Core(
    val memoryArray: MemoryArray = MemoryArray(),
    val registerArray: RegisterArray = RegisterArray(),
    private val program: Program,
) {

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
