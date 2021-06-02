package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.getExecutionEnvironment
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class AddRegisterTest {
    @Test
    internal fun `add register with positive values in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, 10.W)
            registerArray.setValueAtRegister(2.DR, 20.W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` 30.W
    }

    @Test
    internal fun `add register with one positive and one negative value in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, 10.W)
            registerArray.setValueAtRegister(2.DR, (-15).W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-5).W
    }

    @Test
    internal fun `add register with one negative and one positive value in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, (-15).W)
            registerArray.setValueAtRegister(2.DR, 10.W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-5).W
    }

    @Test
    internal fun `add register with negative values in register calculates correct value`() {
        val executionEnvironment = getExecutionEnvironment().apply {
            registerArray.setValueAtRegister(1.DR, (-15).W)
            registerArray.setValueAtRegister(2.DR, (-10).W)
        }

        val addInstruction = Add.of(3.DR, 1.SR, 2.SR)
        addInstruction.execute(executionEnvironment)

        executionEnvironment.registerArray.getRegisterAt(3.DR).getRegisterValue() `should be equal to` (-25).W
    }
}
