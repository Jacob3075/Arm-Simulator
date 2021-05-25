package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.createAddInstruction
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class AddImmediateTest {
    @Test
    internal fun `executing add instruction with source register and immediate value reads and updates the correct registers`() {
        val registerArray = RegisterArray()
        registerArray.setValueAtRegister(0.RA, 10.W)

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray)

        val sourceRegister1 = 0.RA
        val destinationRegister = 2.RA
        val immediateValue = 20

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, immediateValue)

        addInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 30.W
    }

    @Test
    internal fun `running add instructions using immediate value reads and updates the correct registers`() {
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 20.W)

        val add1 = createAddInstruction(registerAddress3, registerAddress1, 20)
        val add2 = createAddInstruction(registerAddress4, registerAddress2, 30)

        val instructions: List<Instruction> = listOf(
            add1,
            add2,
        )
        val program = Program(instructions, variables)

        val core = Core(registerArray = registerArray, program = program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 30.W
        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 50.W
    }

    @Test
    internal fun `running add instructions using immediate value with left shift reads and updates the correct registers`() {
        val registerArray = RegisterArray()
        val variables = emptyList<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 20.W)

        val add1 = Add.of(
            destinationRegister = DestinationRegister(registerAddress3),
            sourceRegister1 = SourceRegister(registerAddress1),
            immediateValue = 10.I,
            shiftOperation = LeftShift(1)
        )
        val add2 = Add.of(
            destinationRegister = DestinationRegister(registerAddress4),
            sourceRegister1 = SourceRegister(registerAddress2),
            immediateValue = 10.I,
            shiftOperation = LeftShift(3)
        )

        val instructions: List<Instruction> = listOf(
            add1,
            add2,
        )
        val program = Program(instructions, variables)

        val core = Core(registerArray = registerArray, program = program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 30.W
        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 100.W
    }
}
