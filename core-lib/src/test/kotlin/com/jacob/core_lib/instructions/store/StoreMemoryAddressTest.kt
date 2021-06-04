package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.*
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class StoreMemoryAddressTest {
    @Test
    internal fun `reads correct register and updates correct memory address`() {
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val memoryAddress1 = 0.MA
        val memoryAddress2 = 2.MA
        val sourceRegister1 = 0.SR
        val sourceRegister2 = 1.SR

        registerArray.setValueAtRegister(sourceRegister1, 10.W)
        registerArray.setValueAtRegister(sourceRegister2, 20.W)

        val store1 = StoreMemoryAddress(memoryAddress1, sourceRegister1)
        val store2 = StoreMemoryAddress(memoryAddress2, sourceRegister2)

        store1.execute(executionEnvironment)
        registerArray.getRegisterAt(sourceRegister1).getRegisterValue() `should be equal to` 10.W

        store2.execute(executionEnvironment)
        registerArray.getRegisterAt(sourceRegister2).getRegisterValue() `should be equal to` 20.W
    }

    @Test
    internal fun `running store instructions reads and updates correct register and memory addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedVariable>()

        val memoryAddress1 = 0.MA
        val memoryAddress2 = 2.MA
        val sourceRegister1 = 1.SR
        val sourceRegister2 = 2.SR

        registerArray.setValueAtRegister(sourceRegister1, 10.W)
        registerArray.setValueAtRegister(sourceRegister2, 20.W)

        val store1 = StoreMemoryAddress(memoryAddress1, sourceRegister1)
        val store2 = StoreMemoryAddress(memoryAddress2, sourceRegister2)

        val instructions: List<Instruction> = listOf(
            store1,
            store2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        memoryArray.getWordAt(memoryAddress1) `should be equal to` 10.W
        memoryArray.getWordAt(memoryAddress2) `should be equal to` 20.W
    }
}
