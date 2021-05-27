package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.*
import com.jacob.core_lib.getExecutionEnvironment
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class StoreRegisterAddressWithImmediateOffsetTest {
    @Test
    internal fun `reads and updates correct memory address when given positive offset value`() {
        val sourceRegister = 1.SR
        val destinationRegister = 0.DR
        val offset = 3.I

        val executionEnvironment = getExecutionEnvironment()
        val (registerArray, memoryArray) = executionEnvironment

        registerArray.setValueAtRegister(1.SR, 10.W)
        registerArray.setValueAtRegister(0.DR, 6.W)

        val loadInstruction = StoreRegisterAddressWithImmediateOffset(sourceRegister, destinationRegister, offset)
        loadInstruction.execute(executionEnvironment)

        memoryArray.getWordAt(9.MA) `should be equal to` 10.W
        registerArray.getRegisterAt(0.DR).getRegisterValue() `should be equal to` 6.W
    }

    @Test
    internal fun `reads and updates correct memory address when given negative offset value`() {
        val sourceRegister = 1.SR
        val destinationRegister = 0.DR
        val offset = (-3).I

        val executionEnvironment = getExecutionEnvironment()
        val (registerArray, memoryArray) = executionEnvironment

        registerArray.setValueAtRegister(1.SR, 10.W)
        registerArray.setValueAtRegister(0.DR, 6.W)

        val loadInstruction = StoreRegisterAddressWithImmediateOffset(sourceRegister, destinationRegister, offset)
        loadInstruction.execute(executionEnvironment)

        memoryArray.getWordAt(3.MA) `should be equal to` 10.W
        registerArray.getRegisterAt(0.DR).getRegisterValue() `should be equal to` 6.W
    }
}
