package com.jacob.core_lib.instructions.store

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

internal class StoreMemoryAddressTest {
    @Test
    internal fun `reads correct register and updates correct memory address`() {
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val sourceRegister1 = SourceRegister(RegisterAddresses.REGISTER_0)
        val sourceRegister2 = SourceRegister(RegisterAddresses.REGISTER_1)

        registerArray.setValueAtRegister(sourceRegister1, Word(10))
        registerArray.setValueAtRegister(sourceRegister2, Word(20))

        val store1 = StoreMemoryAddress(memoryAddress1, sourceRegister1)
        val store2 = StoreMemoryAddress(memoryAddress2, sourceRegister2)

        store1.execute(executionEnvironment)
        registerArray.getRegisterAt(sourceRegister1)
            .getRegisterValue() `should be equal to` Word(10)

        store2.execute(executionEnvironment)
        registerArray.getRegisterAt(sourceRegister2)
            .getRegisterValue() `should be equal to` Word(20)
    }

    @Test
    internal fun `running store instructions reads and updates correct register and memory addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val sourceRegister1 = SourceRegister(RegisterAddresses.REGISTER_1)
        val sourceRegister2 = SourceRegister(RegisterAddresses.REGISTER_2)

        registerArray.setValueAtRegister(sourceRegister1, Word(10))
        registerArray.setValueAtRegister(sourceRegister2, Word(20))

        val store1 = StoreMemoryAddress(memoryAddress1, sourceRegister1)
        val store2 = StoreMemoryAddress(memoryAddress2, sourceRegister2)

        val instructions: List<Instruction> = listOf(
            store1,
            store2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        memoryArray.getWordAt(memoryAddress1) `should be equal to` Word(10)
        memoryArray.getWordAt(memoryAddress2) `should be equal to` Word(20)
    }
}
