package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LoadRegisterAddressTest {
    @Test
    internal fun `executing instruction reads and updates correct registers`() {
        val sourceRegister = 0.SR
        val destinationRegister = 1.DR

        val loadRegisterAddress = LoadRegisterAddress(destinationRegister, sourceRegister)

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()

        registerArray.setValueAtRegister(sourceRegister, 5.W)
        memoryArray.setWordAt(5.MA, 10.W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray, memoryArray = memoryArray)

        loadRegisterAddress.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 10.W
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val sourceRegister1 = 0.SR
        val sourceRegister2 = 3.SR
        val destinationRegister1 = 1.DR
        val destinationRegister2 = 2.DR

        registerArray.setValueAtRegister(sourceRegister1, 1.W)
        registerArray.setValueAtRegister(sourceRegister2, 2.W)

        memoryArray.setWordAt(1.MA, 10.W)
        memoryArray.setWordAt(2.MA, 20.W)

        val load1 = LoadRegisterAddress(destinationRegister1, sourceRegister1)
        val load2 = LoadRegisterAddress(destinationRegister2, sourceRegister2)

        val instructions: List<Instruction> = listOf(
            load1,
            load2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1).getRegisterValue() `should be equal to` 10.W

        registerArray.getRegisterAt(destinationRegister2).getRegisterValue() `should be equal to` 20.W
    }
}
