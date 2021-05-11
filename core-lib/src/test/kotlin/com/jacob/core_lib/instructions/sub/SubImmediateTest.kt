package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createSubInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class SubImmediateTest {

    @Test
    internal fun `can create new sub instruction with source register and immediate value`() {
        val sourceRegister1 = RegisterAddress.REGISTER_0
        val destinationRegister = RegisterAddress.REGISTER_2
        val immediateValue = 10

        val addInstruction = createSubInstruction(destinationRegister, sourceRegister1, immediateValue)

        addInstruction.shouldNotBeNull()
        addInstruction `should be instance of` SubImmediate::class
        addInstruction `should be instance of` Sub::class
        addInstruction `should be instance of` Instruction::class
    }

    @Test
    internal fun `executing sub instruction with source register and immediate value reads and updates the correct registers`() {
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

        val sourceRegister = RegisterAddress.REGISTER_0
        val destinationRegister = RegisterAddress.REGISTER_2
        val immediateValue = 10

        registerArray.setValueAtRegister(sourceRegister, Word(20))

        val addInstruction = createSubInstruction(destinationRegister, sourceRegister, immediateValue)

        addInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` Word(10)
    }
}
