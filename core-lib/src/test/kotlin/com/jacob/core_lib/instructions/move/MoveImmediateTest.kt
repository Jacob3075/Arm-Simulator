package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createMoveInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.instructions.shift.RightShift
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class MoveImmediateTest {
    @Test
    internal fun `executing instruction updates the register array`() {
        val memoryArray = MemoryArray()
        val labels = emptyList<Label>()
        val variables = emptyList<Variable>()
        val registerArray = RegisterArray()
        val immediateValue = 20.I
        val destinationRegister = DestinationRegister(0.RA)

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
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA

        val move1 = createMoveInstruction(registerAddress1, 10)
        val move2 = createMoveInstruction(registerAddress2, 20)

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1).getRegisterValue() `should be equal to` 10.W
        registerArray.getRegisterAt(registerAddress2).getRegisterValue() `should be equal to` 20.W
    }

    @Test
    internal fun `running move instructions with left shift updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA

        val move1 = Move.of(DestinationRegister(registerAddress1), 10.I, LeftShift(1))
        val move2 = Move.of(DestinationRegister(registerAddress2), 20.I, LeftShift(3))

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1).getRegisterValue() `should be equal to` 20.W
        registerArray.getRegisterAt(registerAddress2).getRegisterValue() `should be equal to` 160.W
    }

    @Test
    internal fun `running move instructions with right shift updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA

        val move1 = Move.of(DestinationRegister(registerAddress1), 16.I, RightShift(2))
        val move2 = Move.of(DestinationRegister(registerAddress2), 160.I, RightShift(3))

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1).getRegisterValue() `should be equal to` 4.W
        registerArray.getRegisterAt(registerAddress2).getRegisterValue() `should be equal to` 20.W
    }
}
