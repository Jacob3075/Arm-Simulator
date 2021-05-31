package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.*
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.registers.StatusRegister
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SubRegisterTest {
    @Test
    internal fun `sub register with positive values in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, 20.W)
            registerArray.setValueAtRegister(2.DR, 10.W)
        }

        val addInstruction = Sub.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 10.W
    }

    @Test
    internal fun `sub register with one positive and one negative value in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, 10.W)
            registerArray.setValueAtRegister(2.DR, (-15).W)
        }

        val addInstruction = Sub.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 25.W
    }

    @Test
    internal fun `sub register with one negative and one positive value in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, (-15).W)
            registerArray.setValueAtRegister(2.DR, 10.W)
        }

        val addInstruction = Sub.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-25).W
    }

    @Test
    internal fun `sub register with negative values in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, (-15).W)
            registerArray.setValueAtRegister(2.DR, (-10).W)
        }

        val addInstruction = Sub.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-5).W
    }

    @Nested
    inner class WithShiftOperations {
        @Test
        internal fun `applying left shift to register with positive value`() {
            val executionEnvironment = getExecutionEnvironment().apply {
                registerArray.setValueAtRegister(1.DR, 15.W)
                registerArray.setValueAtRegister(2.DR, 10.W)
            }

            val addInstruction = Sub.of(3.DR, 1.SR, 2.SR, 2.LS)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-25).W
        }

        @Test
        internal fun `applying right shift to register with negative value`() {
            val executionEnvironment = getExecutionEnvironment().apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
                registerArray.setValueAtRegister(2.DR, (-12).W)
            }

            val addInstruction = Sub.of(3.DR, 1.SR, 2.SR, 2.RS)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR)
                .getRegisterValue() `should be equal to` (-1073741811).W
        }
    }

    @Nested
    inner class WithConditionals {
        @Test
        internal fun `instruction with equals conditional and its satisfied`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(zero = true))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 20.W)
                registerArray.setValueAtRegister(2.DR, 10.W)
            }

            val addInstruction = Sub.of(3.DR, 1.SR, 2.SR, conditional = Conditionals.EQ)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 10.W
        }

        @Test
        internal fun `instruction with equals conditional and its not satisfied`() {
            val executionEnvironment = getExecutionEnvironment().apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
                registerArray.setValueAtRegister(2.DR, 20.W)
            }

            val addInstruction = Sub.of(3.DR, 1.SR, 2.SR, conditional = Conditionals.EQ)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 0.W
        }
    }
}
