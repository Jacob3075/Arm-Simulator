package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.*
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LoadMemoryAddressTest {

    @Test
    internal fun `reads correct memory location and updates correct register`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray, memoryArray = memoryArray)

        val memoryAddress1 = 0.MA
        val memoryAddress2 = 2.MA
        val destinationRegister1 = 0.DR
        val destinationRegister2 = 0.DR

        memoryArray.setWordAt(memoryAddress1, 10.W)
        memoryArray.setWordAt(memoryAddress2, 20.W)

        val load1 = LoadMemoryAddress(destinationRegister1, memoryAddress1)
        val load2 = LoadMemoryAddress(destinationRegister2, memoryAddress2)

        load1.execute(executionEnvironment)
        registerArray.getRegisterAt(destinationRegister1).getRegisterValue() `should be equal to` 10.W

        load2.execute(executionEnvironment)
        registerArray.getRegisterAt(destinationRegister2).getRegisterValue() `should be equal to` 20.W
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedVariable>()

        val memoryAddress1 = 0.MA
        val memoryAddress2 = 2.MA
        val destinationRegister1 = 1.DR
        val destinationRegister2 = 2.DR

        memoryArray.setWordAt(memoryAddress1, 10.W)
        memoryArray.setWordAt(memoryAddress2, 20.W)

        val load1 = LoadMemoryAddress(destinationRegister1, memoryAddress1)
        val load2 = LoadMemoryAddress(destinationRegister2, memoryAddress2)

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
