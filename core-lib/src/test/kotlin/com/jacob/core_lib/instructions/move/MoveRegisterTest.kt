package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createMoveInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveRegisterTest {
    @Test
    internal fun `can create move instruction with source register`() {
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_0)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)

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
        val sourceRegister = SourceRegister(RegisterAddress.REGISTER_0)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_0, Word(10))

        val moveInstruction = Move.of(destinationRegister, sourceRegister)
        moveInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` Word(10)
    }

    @Test
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val destinationAddress1 = RegisterAddress.REGISTER_0
        val destinationAddress2 = RegisterAddress.REGISTER_1
        val sourceAddress1 = RegisterAddress.REGISTER_2
        val sourceAddress2 = RegisterAddress.REGISTER_3

        val move1 = createMoveInstruction(sourceAddress1, 10)
        val move2 = createMoveInstruction(sourceAddress2, 20)
        val move3 = createMoveInstruction(destinationAddress1, sourceAddress1)
        val move4 = createMoveInstruction(destinationAddress2, sourceAddress2)

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
            move3,
            move4,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationAddress1)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(destinationAddress2)
            .getRegisterValue() `should be equal to` Word(20)

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

}
