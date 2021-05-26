package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.W
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

internal class LoadRegisterAddressTest {
    @Test
    internal fun `executing instruction reads and updates correct registers`() {
        val sourceRegister = SourceRegister(RegisterAddresses.REGISTER_0)
        val destinationRegister = DestinationRegister(RegisterAddresses.REGISTER_1)

        val loadRegisterAddress = LoadRegisterAddress(destinationRegister, sourceRegister)

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()

        registerArray.setValueAtRegister(sourceRegister, Word(5))
        memoryArray.setWordAt(MemoryAddress(5), Word(10))

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray, memoryArray = memoryArray)

        loadRegisterAddress.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` Word(10)
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val sourceRegister1 = SourceRegister(RegisterAddresses.REGISTER_0)
        val sourceRegister2 = SourceRegister(RegisterAddresses.REGISTER_3)
        val destinationRegister1 = DestinationRegister(RegisterAddresses.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddresses.REGISTER_2)

        registerArray.setValueAtRegister(sourceRegister1, Word(1))
        registerArray.setValueAtRegister(sourceRegister2, Word(2))

        memoryArray.setWordAt(MemoryAddress(1), Word(10))
        memoryArray.setWordAt(MemoryAddress(2), Word(20))

        val load1 = LoadRegisterAddress(destinationRegister1, sourceRegister1)
        val load2 = LoadRegisterAddress(destinationRegister2, sourceRegister2)

        val instructions: List<Instruction> = listOf(
            load1,
            load2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1).getRegisterValue() `should be equal to` 10.W

        registerArray.getRegisterAt(destinationRegister2).getRegisterValue() `should be equal to` 20.W
    }
}
