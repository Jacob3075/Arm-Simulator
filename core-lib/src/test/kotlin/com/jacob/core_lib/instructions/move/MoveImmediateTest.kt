package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
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

internal class MoveImmediateTest {
    @Test
    internal fun `executing instruction updates the register array`() {
        val registerArray = RegisterArray()
        val immediateValue = 20.I
        val destinationRegister = 0.DR

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val moveInstruction = Move.of(destinationRegister, immediateValue)
        moveInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` immediateValue
    }

    @Test
    internal fun `running move instructions updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.DR
        val registerAddress2 = 2.DR

        val move1 = Move.of(registerAddress1, 10.I)
        val move2 = Move.of(registerAddress2, 20.I)

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
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.DR
        val registerAddress2 = 2.DR

        val move1 = Move.of(registerAddress1, 10.I, LeftShift(1))
        val move2 = Move.of(registerAddress2, 20.I, LeftShift(3))

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
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.DR
        val registerAddress2 = 2.DR

        val move1 = Move.of(registerAddress1, 16.I, RightShift(2))
        val move2 = Move.of(registerAddress2, 160.I, RightShift(3))

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
