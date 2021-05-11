package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createAddInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class AddRegisterTest {
    @Test
    internal fun `can create new add instruction with 2 source registers`() {
        val sourceRegister1 = RegisterAddress.REGISTER_0
        val sourceRegister2 = RegisterAddress.REGISTER_0
        val destinationRegister = RegisterAddress.REGISTER_2

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        addInstruction.shouldNotBeNull()
        addInstruction `should be instance of` AddRegister::class
        addInstruction `should be instance of` Add::class
        addInstruction `should be instance of` Instruction::class
    }

    @Test
    internal fun `executing add instruction with register values reads and updates the correct registers`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()
        val registerArray = RegisterArray()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_0, Word(10))
        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(20))

        val sourceRegister1 = RegisterAddress.REGISTER_0
        val sourceRegister2 = RegisterAddress.REGISTER_1
        val destinationRegister = RegisterAddress.REGISTER_2

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        addInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` Word(30)
    }
}
