package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createAddInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class AddRegisterTest {
    @Test
    internal fun `executing add instruction with register values reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val labels = emptyList<Label>()
        val variables = emptyList<Variable>()
        val registerArray = RegisterArray()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(0.RA, 10.W)
        registerArray.setValueAtRegister(1.RA, 20.W)

        val sourceRegister1 = 1.RA
        val sourceRegister2 = 2.RA
        val destinationRegister = 3.RA

        val addInstruction = createAddInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        addInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 30.W
    }

    @Test
    internal fun `running add instructions using registers reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 20.W)

        val add1 = createAddInstruction(registerAddress3, registerAddress1, registerAddress2)
        val add2 = createAddInstruction(registerAddress4, registerAddress2, registerAddress3)

        val instructions: List<Instruction> = listOf(
            add1,
            add2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 30.W

        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 50.W
    }

    @Test
    internal fun `running add instructions using registers with left shifts reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 10.W)

        val add1 = Add.of(
            DestinationRegister(registerAddress3),
            SourceRegister(registerAddress1),
            SourceRegister(registerAddress2),
            LeftShift(1)
        )
        val add2 = Add.of(
            DestinationRegister(registerAddress4),
            SourceRegister(registerAddress2),
            SourceRegister(registerAddress3),
            LeftShift(3)
        )

        val instructions: List<Instruction> = listOf(
            add1,
            add2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 30.W

        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 250.W
    }
}
