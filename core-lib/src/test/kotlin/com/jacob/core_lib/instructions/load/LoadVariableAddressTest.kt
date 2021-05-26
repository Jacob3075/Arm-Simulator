package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.*
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LoadVariableAddressTest {

    @Test
    internal fun `executing instruction should read and update correct address`() {
        val destinationRegister = 0.DR
        val variable = Variable("A", 0.MA)

        val loadVariableAddress = LoadVariableAddress(destinationRegister, variable.name)

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()
        val variables = listOf(variable)

        memoryArray.setWordAt(variable.address, 10.W)

        val executionEnvironment =
            getExecutionEnvironment(registerArray = registerArray, memoryArray = memoryArray, variables = variables)

        loadVariableAddress.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 0.W
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf(
            ParsedData("A", 10),
            ParsedData("ABC", 20)
        )

        val destinationRegister1 = 1.DR
        val destinationRegister2 = 2.DR

        val load1 = LoadVariableAddress(destinationRegister1, "A")
        val load2 = LoadVariableAddress(destinationRegister2, "ABC")

        val instructions: List<Instruction> = listOf(
            load1,
            load2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1).getRegisterValue() `should be equal to` 0.W
        registerArray.getRegisterAt(destinationRegister2).getRegisterValue() `should be equal to` 1.W
    }
}
