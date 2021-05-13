package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createMoveInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.word.ImmediateValue
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveImmediateTest {

    @Test
    internal fun `create new move immediate instruction`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)
        val immediateValue = ImmediateValue(50)
        val moveInstruction = Move.of(destinationRegister, immediateValue)

        moveInstruction.shouldNotBeNull()
        moveInstruction `should be instance of` Instruction::class
        moveInstruction `should be instance of` Move::class
        moveInstruction `should be instance of` MoveImmediate::class
    }

    @Test
    internal fun `executing instruction updates the register array`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()
        val registerArray = RegisterArray()
        val immediateValue = ImmediateValue(20)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_0)

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        val moveInstruction = Move.of(destinationRegister, immediateValue)
        moveInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` immediateValue
    }

    @Test
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val registerAddress1 = RegisterAddress.REGISTER_1
        val registerAddress2 = RegisterAddress.REGISTER_2

        val move1 = createMoveInstruction(registerAddress1, 10)
        val move2 = createMoveInstruction(registerAddress2, 20)

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(registerAddress2)
            .getRegisterValue() `should be equal to` Word(20)

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

}
