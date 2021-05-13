package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class StoreVariableAddressTest {
    @Test
    internal fun `executing instruction reads and updates correct registers and memory array positions`() {
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_1)
        val variableName = "A"

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()
        val labels = mockk<List<Label>>()
        val variable = Variable("A", MemoryAddress(5))
        val variables = listOf(variable)

        registerArray.setValueAtRegister(sourceRegister.registerAddress, Word(10))

        val executionEnvironment = ExecutionEnvironment(registerArray, memoryArray, labels, variables)

        val storeVariableAddress = StoreVariableAddress(sourceRegister, variableName)
        storeVariableAddress.execute(executionEnvironment)

        memoryArray.getWordAt(variable.address) `should be equal to` Word(10)
    }
}
