package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createMoveInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.instructions.shift.RightShift
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class MoveRegisterTest {
    @Test
    internal fun `executing instruction updates the register array`() {
        val memoryArray = MemoryArray()
        val labels = emptyList<Label>()
        val variables = emptyList<Variable>()
        val registerArray = RegisterArray()
        val sourceRegister = SourceRegister(0.RA)
        val destinationRegister = DestinationRegister(0.RA)

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(0.RA, 10.W)

        val moveInstruction = Move.of(destinationRegister, sourceRegister)
        moveInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister.registerAddress).getRegisterValue() `should be equal to` 10.W
    }

    @Test
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val destinationAddress1 = 0.RA
        val destinationAddress2 = 1.RA
        val sourceAddress1 = 2.RA
        val sourceAddress2 = 3.RA

        registerArray.setValueAtRegister(sourceAddress1, 10.I)
        registerArray.setValueAtRegister(sourceAddress2, 20.I)

        val move1 = createMoveInstruction(destinationAddress1, sourceAddress1)
        val move2 = createMoveInstruction(destinationAddress2, sourceAddress2)

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationAddress1).getRegisterValue() `should be equal to` 10.W
        registerArray.getRegisterAt(destinationAddress2).getRegisterValue() `should be equal to` 20.W
    }

    @Test
    internal fun `running move instructions with left shift updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val destinationAddress1 = 0.RA
        val destinationAddress2 = 1.RA
        val sourceAddress1 = 2.RA
        val sourceAddress2 = 3.RA

        registerArray.setValueAtRegister(sourceAddress1, 10.I)
        registerArray.setValueAtRegister(sourceAddress2, 20.I)

        val move1 = Move.of(DestinationRegister(destinationAddress1), SourceRegister(sourceAddress1), LeftShift(1))
        val move2 = Move.of(DestinationRegister(destinationAddress2), SourceRegister(sourceAddress2), LeftShift(2))

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationAddress1).getRegisterValue() `should be equal to` 20.W
        registerArray.getRegisterAt(destinationAddress2).getRegisterValue() `should be equal to` 80.W
    }

    @Test
    internal fun `running move instructions with right shift updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val destinationAddress1 = 0.RA
        val destinationAddress2 = 1.RA
        val sourceAddress1 = 2.RA
        val sourceAddress2 = 3.RA

        registerArray.setValueAtRegister(sourceAddress1, 10.I)
        registerArray.setValueAtRegister(sourceAddress2, 20.I)

        val move1 = Move.of(DestinationRegister(destinationAddress1), SourceRegister(sourceAddress1), RightShift(1))
        val move2 = Move.of(DestinationRegister(destinationAddress2), SourceRegister(sourceAddress2), RightShift(2))

        val instructions: List<Instruction> = listOf(
            move1,
            move2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(destinationAddress1).getRegisterValue() `should be equal to` 5.W
        registerArray.getRegisterAt(destinationAddress2).getRegisterValue() `should be equal to` 5.W
    }
}
