package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class StoreTest {
    @Test
    internal fun `can create store instruction`() {
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_1)
        val destinationAddress = MemoryAddress(0)

        val storeInstruction = Store(sourceRegister, destinationAddress)

        storeInstruction.shouldNotBeNull()
    }

    @Test
    internal fun `reads correct register and updates correct memory address`() {
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val sourceRegister1 = SourceRegister(RegisterAddress.REGISTER_1)
        val sourceRegister2 = SourceRegister(RegisterAddress.REGISTER_2)

        registerArray.setValueAtRegister(sourceRegister1.registerAddress, Word(10))
        registerArray.setValueAtRegister(sourceRegister2.registerAddress, Word(20))

        val store1 = Store(sourceRegister1, memoryAddress1)
        val store2 = Store(sourceRegister2, memoryAddress2)

        store1.execute(executionEnvironment)
        registerArray.getRegisterAt(sourceRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        store2.execute(executionEnvironment)
        registerArray.getRegisterAt(sourceRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }
}
