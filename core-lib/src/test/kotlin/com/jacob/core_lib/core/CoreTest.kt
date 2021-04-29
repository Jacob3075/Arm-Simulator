package com.jacob.core_lib.core

import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.Label
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
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_2)
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

        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_4).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_6).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
    }

    @Test
    internal fun `running branch instructions jump to the correct point`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val destinationRegister1 = DestinationRegister(RegisterAddress.REGISTER_1)
        val destinationRegister2 = DestinationRegister(RegisterAddress.REGISTER_2)
        val destinationRegister6 = DestinationRegister(RegisterAddress.REGISTER_6)
        val immediateValue1 = ImmediateValue(10)
        val immediateValue2 = ImmediateValue(20)
        val immediateValue3 = ImmediateValue(30)
        val labelName = "LABEL1"

        val instructions: List<Instruction> = listOf(
            Move(destinationRegister1, immediateValue1),
            Branch(labelName),
            Move(destinationRegister2, immediateValue2),
            Label(labelName),
            Move(destinationRegister6, immediateValue3),
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationRegister1.registerAddress)
            .getRegisterValue() `should be equal to` immediateValue1

        registerArray.getRegisterAt(destinationRegister6.registerAddress)
            .getRegisterValue() `should be equal to` immediateValue3

        registerArray.getRegisterAt(RegisterAddress.REGISTER_2).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_4).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
    }
}
