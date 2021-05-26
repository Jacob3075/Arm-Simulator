package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.word.Word
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class StoreRegisterAddressTest {
    @Test
    internal fun `executing instruction reads and updates correct registers`() {
        val sourceRegister = SourceRegister(RegisterAddresses.REGISTER_1)
        val destinationRegister = DestinationRegister(RegisterAddresses.REGISTER_0)

        val storeRegisterAddress = StoreRegisterAddress(sourceRegister, destinationRegister)

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()

        registerArray.setValueAtRegister(sourceRegister, Word(10))
        registerArray.setValueAtRegister(destinationRegister, Word(5))

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray, memoryArray = memoryArray)

        storeRegisterAddress.execute(executionEnvironment)

        memoryArray.getWordAt(MemoryAddress(5)) `should be equal to` Word(10)
    }

    @Test
    internal fun `running store instructions reads and updates correct register and memory addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val sourceRegister1 = SourceRegister(RegisterAddresses.REGISTER_1)
        val sourceRegister2 = SourceRegister(RegisterAddresses.REGISTER_2)
        val destinationRegister1 = DestinationRegister(RegisterAddresses.REGISTER_3)
        val destinationRegister2 = DestinationRegister(RegisterAddresses.REGISTER_4)

        registerArray.setValueAtRegister(destinationRegister1, Word(1))
        registerArray.setValueAtRegister(destinationRegister2, Word(2))
        registerArray.setValueAtRegister(sourceRegister1, Word(10))
        registerArray.setValueAtRegister(sourceRegister2, Word(20))

        val store1 = StoreRegisterAddress(sourceRegister1, destinationRegister1)
        val store2 = StoreRegisterAddress(sourceRegister2, destinationRegister2)

        val instructions: List<Instruction> = listOf(
            store1,
            store2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        memoryArray.getWordAt(MemoryAddress(1)) `should be equal to` Word(10)
        memoryArray.getWordAt(MemoryAddress(2)) `should be equal to` Word(20)
    }
}
