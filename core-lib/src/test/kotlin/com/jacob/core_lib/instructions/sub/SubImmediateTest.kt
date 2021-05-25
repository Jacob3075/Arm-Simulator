package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.createSubInstruction
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.instructions.shift.RightShift
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class SubImmediateTest {
    @Test
    internal fun `executing sub instruction with source register and immediate value reads and updates the correct registers`() {
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val sourceRegister = 0.RA
        val destinationRegister = 2.RA
        val immediateValue = 10

        registerArray.setValueAtRegister(sourceRegister, 20.W)

        val addInstruction = createSubInstruction(destinationRegister, sourceRegister, immediateValue)

        addInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 10.W
    }

    @Test
    internal fun `running sub instructions using immediate value reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 30.W)
        registerArray.setValueAtRegister(registerAddress2, 20.W)

        val sub1 = createSubInstruction(registerAddress3, registerAddress1, 20)
        val sub2 = createSubInstruction(registerAddress4, registerAddress2, 10)

        val instructions: List<Instruction> = listOf(
            sub1,
            sub2,
        )

        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 10.W
        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 10.W
    }

    @Test
    internal fun `running sub instructions using immediate value with left shit reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 50.W)
        registerArray.setValueAtRegister(registerAddress2, 100.W)

        val sub1 = Sub.of(DestinationRegister(registerAddress3), SourceRegister(registerAddress1), 20.I, LeftShift(1))
        val sub2 = Sub.of(DestinationRegister(registerAddress4), SourceRegister(registerAddress2), 10.I, LeftShift(3))

        val instructions: List<Instruction> = listOf(
            sub1,
            sub2,
        )

        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 10.W
        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 20.W
    }

    @Test

    internal fun `running sub instructions using immediate value with right shit reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 50.W)
        registerArray.setValueAtRegister(registerAddress2, 100.W)

        val sub1 = Sub.of(DestinationRegister(registerAddress3), SourceRegister(registerAddress1), 20.I, RightShift(1))
        val sub2 = Sub.of(DestinationRegister(registerAddress4), SourceRegister(registerAddress2), 80.I, RightShift(2))

        val instructions: List<Instruction> = listOf(
            sub1,
            sub2,
        )

        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 40.W
        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 80.W
    }
}
