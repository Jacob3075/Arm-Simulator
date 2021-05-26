package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.*
import com.jacob.core_lib.getExecutionEnvironment
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LoadRegisterAddressWithPostOffsetTest {
    @Test
    internal fun `reads and updates correct memory address when given positive offset value`() {
        val destinationRegister = 1.DR
        val sourceRegister = 0.SR
        val offset = 3.I

        val executionEnvironment = getExecutionEnvironment()
        val (registerArray, memoryArray) = executionEnvironment

        memoryArray.setWordAt(0.MA, 15.W)
        memoryArray.setWordAt(3.MA, 10.W)
        registerArray.setValueAtRegister(0.DR, 0.W)

        val loadInstruction = LoadRegisterAddressWithPostOffset(destinationRegister, sourceRegister, offset)
        loadInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 15.W
        registerArray.getRegisterAt(sourceRegister).getRegisterValue() `should be equal to` 3.W
    }

    @Test
    internal fun `reads and updates correct memory address when given negative offset value`() {
        val destinationRegister = 1.DR
        val sourceRegister = 0.SR
        val offset = (-3).I

        val executionEnvironment = getExecutionEnvironment()
        val (registerArray, memoryArray) = executionEnvironment

        memoryArray.setWordAt(0.MA, 15.W)
        memoryArray.setWordAt(3.MA, 10.W)
        registerArray.setValueAtRegister(0.DR, 3.W)

        val loadInstruction = LoadRegisterAddressWithPostOffset(destinationRegister, sourceRegister, offset)
        loadInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 10.W
        registerArray.getRegisterAt(sourceRegister).getRegisterValue() `should be equal to` 0.W
    }
}
