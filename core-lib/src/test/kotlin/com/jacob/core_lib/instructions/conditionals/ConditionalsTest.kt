package com.jacob.core_lib.instructions.conditionals

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.conditionals.Conditionals.*
import com.jacob.core_lib.registers.StatusRegister
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ConditionalsTest {
    @Nested
    inner class `When Equals Condition is Given` {
        @Test
        internal fun `and zero bit is set, instruction should be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(zero = true))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = EQ)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
        }

        @Test
        internal fun `and zero bit is not set, instruction should be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(zero = false))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = EQ)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 0.W
        }
    }

    @Nested
    inner class `When Not Equal Condition is Given` {
        @Test
        internal fun `and zero bit is set, instruction should not be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(zero = true))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = NE)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 0.W
        }

        @Test
        internal fun `and zero bit is not set, instruction should be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(zero = false))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = NE)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
        }
    }

    @Nested
    inner class `When Carry Set Condition is Given` {
        @Test
        internal fun `and carry bit is set, instruction should be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(carry = true))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = CS)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
        }

        @Test
        internal fun `and carry bit is not set, instruction should be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(carry = false))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = CS)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 0.W
        }
    }

    @Nested
    inner class `When Carry Clear Condition is Given` {
        @Test
        internal fun `and carry bit is set, instruction should not be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(carry = true))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = CC)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 0.W
        }

        @Test
        internal fun `and carry bit is not set, instruction should be executed`() {
            val registerArray = RegisterArray(statusRegister = StatusRegister(carry = false))
            val executionEnvironment = getExecutionEnvironment(registerArray = registerArray).apply {
                registerArray.setValueAtRegister(1.DR, 10.W)
            }

            val addInstruction = Add.of(3.DR, 1.SR, 20.I, conditional = CC)
            addInstruction.execute(executionEnvironment)

            executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
        }
    }
}
