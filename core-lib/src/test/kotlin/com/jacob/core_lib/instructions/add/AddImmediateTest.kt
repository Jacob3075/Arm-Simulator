package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.*
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.registers.StatusRegister
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class AddImmediateTest {
    @Test
    internal fun `add register with positive values in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, 10.W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, 20.I)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
    }

    @Test
    internal fun `add register with one positive and one negative value in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, 10.W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, (-15).I)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-5).W
    }

    @Test
    internal fun `add register with one negative and one positive value in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, (-15).W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, 10.I)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-5).W
    }

    @Test
    internal fun `add register with negative values in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, (-15).W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, (-10).I)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-25).W
    }

    @Nested
    inner class WithShiftOperations {
        @Test
        internal fun `applying left shift to register with positive value`() {
            val executionEnvironment = getExecutionEnvironment().apply {
                registerArray.setValueAtRegister(1.DR, 15.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 10.I, 2.LS)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 55.W
        }

        @Test
        internal fun `applying right shift to register with negative value`() {
            val executionEnvironment = getExecutionEnvironment().apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, (-12).I, 2.RS)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 1073741831.W
        }
    }

    @Nested
    inner class WithConditionals {
        @Test
        internal fun `instruction with equals conditional and its satisfied`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(zero = true))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = Conditionals.EQ)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
        }

        @Test
        internal fun `instruction with equals conditional and its not satisfied`() {
            val executionEnvironment = getExecutionEnvironment().apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = Conditionals.EQ)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 0.W
        }
    }
}
