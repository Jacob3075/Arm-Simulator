package com.jacob.core_lib.parser.add

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class AddImmediateInstructionParserTest {

    @Test
    internal fun `creates correct add instruction`() {
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

        val instructionString = "ADD R3, R1, #2"

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(1))

        val addImmediateInstruction = AddImmediateInstructionParser(instructionString).invoke()

        addImmediateInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(3)

    }
}
