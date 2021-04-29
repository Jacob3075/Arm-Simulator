package com.jacob.core_lib.core

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.Move
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class CoreTest {

    @Test
    internal fun `can create a new core`() {
        val registerArray = mockk<RegisterArray>()
        val memoryArray = mockk<MemoryArray>()
        val program = mockk<Program>()

        val core = Core(memoryArray, registerArray, program)

        core.shouldNotBeNull()
    }

    @Test
    internal fun `running instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER2)
        val immediateValue1 = ImmediateValue(10)
        val immediateValue2 = ImmediateValue(20)

        val instructions: List<Instruction> = listOf(
            Move(destinationRegister1, immediateValue1), Move(destinationRegister2, immediateValue2)
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` immediateValue1

        registerArray.getRegisterAt(destinationRegister2.registerAddress)
            .getRegisterValue() `should be equal to` immediateValue2

        registerArray.getRegisterAt(RegisterAddress.REGISTER3).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER4).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER6).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER12).getRegisterValue() `should be equal to` Word(0)
    }
}
