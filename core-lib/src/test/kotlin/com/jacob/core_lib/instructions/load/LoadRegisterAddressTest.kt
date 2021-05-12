package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LoadRegisterAddressTest {
    @Test
    internal fun `executing instruction reads and updates correct registers`() {
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_0)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_1)

        val loadRegisterAddress = LoadRegisterAddress(destinationRegister, sourceRegister)

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()

        registerArray.setValueAtRegister(sourceRegister.registerAddress, Word(5))
        memoryArray.setWordAt(MemoryAddress(5), Word(10))

        val executionEnvironment = ExecutionEnvironment(registerArray, memoryArray, labels, variables)

        loadRegisterAddress.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)
    }
}
