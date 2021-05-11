package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class AddRegisterInstructionParserTest {

    @Test
    internal fun `creates correct add instruction`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        val instructionString = "ADD R3, R1, R2"

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(1))
        registerArray.setValueAtRegister(RegisterAddress.REGISTER_2, Word(2))

        val addRegisterInstruction = AddRegisterInstructionParser(instructionString).invoke()

        addRegisterInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(3)
    }

}
