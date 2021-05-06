package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.createAddInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class AddImmediateTest {

    @Test
    internal fun `can create new add instruction with source register and immediate value`() {
        val sourceRegister1 = RegisterAddress.REGISTER_1
        val destinationRegister = RegisterAddress.REGISTER_3
        val immediateValue = 10

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, immediateValue)

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

        val sourceRegister1 = RegisterAddress.REGISTER_1
        val destinationRegister = RegisterAddress.REGISTER_3
        val immediateValue = 20

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, immediateValue)

        addInstruction.execute(memoryArray, registerArray, labels)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` Word(30)
    }
}
