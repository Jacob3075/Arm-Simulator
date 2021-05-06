package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.createSubInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class SubRegisterTest {
    @Test
    internal fun `can create new sub instruction with 2 source registers`() {
        val sourceRegister1 = RegisterAddress.REGISTER_1
        val sourceRegister2 = RegisterAddress.REGISTER_2
        val destinationRegister = RegisterAddress.REGISTER_3

        val subInstruction = createSubInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        subInstruction.shouldNotBeNull()
        subInstruction `should be instance of` SubRegister::class
        subInstruction `should be instance of` Sub::class
        subInstruction `should be instance of` Instruction::class
    }

    @Test
    internal fun `executing sub instruction with register values reads and updates the correct registers`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val registerArray = RegisterArray()

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(20))
        registerArray.setValueAtRegister(RegisterAddress.REGISTER_2, Word(10))

        val sourceRegister1 = RegisterAddress.REGISTER_1
        val sourceRegister2 = RegisterAddress.REGISTER_2
        val destinationRegister = RegisterAddress.REGISTER_3

        val subInstruction = createSubInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        subInstruction.execute(memoryArray, registerArray, labels)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` Word(10)
    }
}
