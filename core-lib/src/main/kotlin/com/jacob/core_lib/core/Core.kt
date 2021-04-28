package com.jacob.core_lib.core


class Core(
    private val memoryArray: MemoryArray,
    private val registerArray: RegisterArray,
    private val program: Program
) {

    fun runProgram(): Unit {
        program.run(memoryArray, registerArray)

    }
}
