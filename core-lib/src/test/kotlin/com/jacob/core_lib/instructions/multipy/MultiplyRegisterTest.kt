package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.DR
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

internal class MultiplyRegisterTest {
    @Test
    internal fun `executing multiply instruction with register values reads and updates the correct registers`() {
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        registerArray.setValueAtRegister(1.DR, 10.W)
        registerArray.setValueAtRegister(2.DR, 20.W)

        val sourceRegister1 = 1.SR
        val sourceRegister2 = 2.SR
        val destinationRegister = 3.DR

        val multiplyInstruction = Multiply.of(
            destinationRegister,
            sourceRegister1,
            sourceRegister2
        )

        multiplyInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 200.W
    }

    @Test
    internal fun `running multiply instructions using registers reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.SR
        val registerAddress2 = 2.SR
        val registerAddress3 = 3.DR
        val registerAddress4 = 4.DR

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 2.W)

        val multiply1 = Multiply.of(
            registerAddress3,
            registerAddress1,
            registerAddress2
        )
        val multiply2 = Multiply.of(
            registerAddress4,
            3.SR,
            registerAddress2
        )

        val instructions: List<Instruction> = listOf(
            multiply1,
            multiply2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 20.W

        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 40.W
    }
}
