package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.instructions.Instruction
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

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val sourceRegister1 = SourceRegister(RegisterAddress.REGISTER_0)
        val sourceRegister2 = SourceRegister(RegisterAddress.REGISTER_3)
        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_2)

        registerArray.setValueAtRegister(sourceRegister1.registerAddress, Word(1))
        registerArray.setValueAtRegister(sourceRegister2.registerAddress, Word(2))

        memoryArray.setWordAt(MemoryAddress(1), Word(10))
        memoryArray.setWordAt(MemoryAddress(2), Word(20))

        val load1 = LoadRegisterAddress(destinationRegister1, sourceRegister1)
        val load2 = LoadRegisterAddress(destinationRegister2, sourceRegister2)

        val instructions: List<Instruction> = listOf(
            load1,
            load2,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(destinationRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }
}
