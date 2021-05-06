package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.registers.address.SourceRegister
import com.jacob.core_lib.word.ImmediateValue
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class AddImmediateTest {

    @Test
    internal fun `can create new add instruction with source register and immediate value`() {
        val sourceRegister1 = SourceRegister(RegisterAddress.REGISTER_1)
        val immediateValue = ImmediateValue(10)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_3)

        val addInstruction = Add.of(destinationRegister, sourceRegister1, immediateValue)

        addInstruction.shouldNotBeNull()
        addInstruction `should be instance of` AddImmediate::class
        addInstruction `should be instance of` Add::class
        addInstruction `should be instance of` Instruction::class
    }

    @Test
    internal fun `executing add instruction with source register and immediate value reads and updates the correct registers`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val registerArray = RegisterArray()

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(10))
        registerArray.setValueAtRegister(RegisterAddress.REGISTER_2, Word(20))

        val sourceRegister1 = SourceRegister(RegisterAddress.REGISTER_1)
        val immediateValue = ImmediateValue(20)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_3)

        val addInstruction = Add.of(destinationRegister, sourceRegister1, immediateValue)

        addInstruction.execute(memoryArray, registerArray, labels)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` Word(30)
    }
}
