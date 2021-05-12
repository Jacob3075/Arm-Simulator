package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.instructions.load.LoadImmediateAddress
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class LoadTest {
    @Test
    internal fun `can create new Load instruction`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)
        val sourceAddress = MemoryAddress(1)

        val loadInstruction = LoadImmediateAddress(destinationRegister, sourceAddress)

        loadInstruction.shouldNotBeNull()
    }

    @Test
    internal fun `reads correct memory location and updates correct register`() {
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
        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_0)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_0)

        memoryArray.setWordAt(memoryAddress1, Word(10))
        memoryArray.setWordAt(memoryAddress2, Word(20))

        val load1 = LoadImmediateAddress(destinationRegister1, memoryAddress1)
        val load2 = LoadImmediateAddress(destinationRegister2, memoryAddress2)

        load1.execute(executionEnvironment)
        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        load2.execute(executionEnvironment)
        registerArray.getRegisterAt(destinationRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }
}
