package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveRegisterTest {
    @Test
    internal fun `can create move instruction with source register`() {
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_1)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_2)

        val moveInstruction = Move.of(destinationRegister, sourceRegister)

        moveInstruction.shouldNotBeNull()
        moveInstruction `should be instance of` Instruction::class
        moveInstruction `should be instance of` Move::class
        moveInstruction `should be instance of` MoveRegister::class
    }

    @Test
    internal fun `executing instruction updates the register array`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()
        val registerArray = RegisterArray()
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_1)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_2)

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(10))

        val moveInstruction = Move.of(destinationRegister, sourceRegister)
        moveInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)
    }
}
