package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LoadVariableAddressTest {

    @Test
    internal fun `executing instruction should read and update correct address`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)
        val variable = Variable("A", MemoryAddress(0))

        val loadVariableAddress = LoadVariableAddress(destinationRegister, variable.name)

        val registerArray = RegisterArray()
        val memoryArray = MemoryArray()
        val labels = mockk<List<Label>>()
        val variables = listOf(variable)

        memoryArray.setWordAt(variable.address, Word(10))

        val executionEnvironment = ExecutionEnvironment(registerArray, memoryArray, labels, variables)
        loadVariableAddress.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf(
            ParsedData("A", 10),
            ParsedData("ABC", 20)
        )

        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_2)

        val load1 = LoadVariableAddress(destinationRegister1, "A")
        val load2 = LoadVariableAddress(destinationRegister2, "ABC")

        val instructions: List<Instruction> = listOf(
            load1,
            load2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(destinationRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }
}
