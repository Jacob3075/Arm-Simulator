package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.instructions.shift.RightShift
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class MoveRegisterTest {
    @Test
    internal fun `executing instruction updates the register array`() {
        val registerArray = RegisterArray()
        val sourceRegister = 0.SR
        val destinationRegister = 0.DR

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)
        registerArray.setValueAtRegister(0.DR, 10.W)

        val moveInstruction = Move.of(destinationRegister, sourceRegister)
        moveInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 10.W
    }

    @Test
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val destinationAddress1 = 0.DR
        val destinationAddress2 = 1.DR
        val sourceAddress1 = 2.SR
        val sourceAddress2 = 3.SR

        registerArray.setValueAtRegister(sourceAddress1, 10.I)
        registerArray.setValueAtRegister(sourceAddress2, 20.I)

        val move1 = Move.of(destinationAddress1, sourceAddress1)
        val move2 = Move.of(destinationAddress2, sourceAddress2)

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
        val variables = emptyList<ParsedData>()

        val destinationAddress1 = 0.DR
        val destinationAddress2 = 1.DR
        val sourceAddress1 = 2.SR
        val sourceAddress2 = 3.SR

        registerArray.setValueAtRegister(sourceAddress1, 10.I)
        registerArray.setValueAtRegister(sourceAddress2, 20.I)

        val move1 = Move.of(destinationAddress1, sourceAddress1, LeftShift(1))
        val move2 = Move.of(destinationAddress2, sourceAddress2, LeftShift(2))

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
        val variables = emptyList<ParsedData>()

        val destinationAddress1 = 0.DR
        val destinationAddress2 = 1.DR
        val sourceAddress1 = 2.SR
        val sourceAddress2 = 3.SR

        registerArray.setValueAtRegister(sourceAddress1, 10.I)
        registerArray.setValueAtRegister(sourceAddress2, 20.I)

        val move1 = Move.of(destinationAddress1, sourceAddress1, RightShift(1))
        val move2 = Move.of(destinationAddress2, sourceAddress2, RightShift(2))

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
