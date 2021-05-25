package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.RegisterAddress
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

internal class LoadMemoryAddressTest {

    @Test
    internal fun `reads correct memory location and updates correct register`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray, memoryArray = memoryArray)

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_0)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_0)

        memoryArray.setWordAt(memoryAddress1, Word(10))
        memoryArray.setWordAt(memoryAddress2, Word(20))

        val load1 = LoadMemoryAddress(destinationRegister1, memoryAddress1)
        val load2 = LoadMemoryAddress(destinationRegister2, memoryAddress2)

        load1.execute(executionEnvironment)
        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)

        load2.execute(executionEnvironment)
        registerArray.getRegisterAt(destinationRegister2.registerAddress)
            .getRegisterValue() `should be equal to` Word(20)
    }

    @Test
    internal fun `running load instructions reads and updates correct memory and register addresses`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val memoryAddress1 = MemoryAddress(0)
        val memoryAddress2 = MemoryAddress(2)
        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_2)

        memoryArray.setWordAt(memoryAddress1, Word(10))
        memoryArray.setWordAt(memoryAddress2, Word(20))

        val load1 = LoadMemoryAddress(destinationRegister1, memoryAddress1)
        val load2 = LoadMemoryAddress(destinationRegister2, memoryAddress2)

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
