package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.*
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class MultiplyImmediateTest {
    @Test
    internal fun `executing multiply instruction with source register and immediate value reads and updates the correct registers`() {
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        registerArray.setValueAtRegister(0.DR, 10.W)

        val sourceRegister1 = 0.SR
        val destinationRegister = 2.DR
        val immediateValue = 20

        val multiplyInstruction =
            Multiply.of(destinationRegister, sourceRegister1, immediateValue.I)

        multiplyInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 200.W
    }

    @Test
    internal fun `running multiply instructions using immediate value reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedVariable>()

        val registerAddress1 = 1.SR
        val registerAddress2 = 2.SR
        val registerAddress3 = 3.DR
        val registerAddress4 = 4.DR

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 20.W)

        val multiply1 = Multiply.of(registerAddress3, registerAddress1, 2.I)
        val multiply2 = Multiply.of(registerAddress4, registerAddress2, 3.I)

        val instructions: List<Instruction> = listOf(
            multiply1,
            multiply2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 20.W
        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 60.W
    }
}
