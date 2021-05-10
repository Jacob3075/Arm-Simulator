package com.jacob.core_lib.parser.move

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class MoveRegisterInstructionParserTest {

    @Test
    internal fun `creates correct move register instruction`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val labels = mockk<List<Label>>()

        val instructionString = "MOV R2, R1"

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(1))

        val moveRegisterInstruction = MoveRegisterInstructionParser(instructionString).invoke()

        moveRegisterInstruction.execute(memoryArray, registerArray, labels)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_2).getRegisterValue() `should be equal to` Word(1)
    }

}
