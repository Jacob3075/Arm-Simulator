package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.*
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class MultiplyRegisterTest {
    @Test
    internal fun `executing multiply instruction with register values reads and updates the correct registers`() {
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

        registerArray.setValueAtRegister(1.RA, 10.W)
        registerArray.setValueAtRegister(2.RA, 20.W)

        val sourceRegister1 = 1.RA
        val sourceRegister2 = 2.RA
        val destinationRegister = 3.RA

        val multiplyInstruction = Multiply.of(
            DestinationRegister(destinationRegister),
            SourceRegister(sourceRegister1),
            SourceRegister(sourceRegister2)
        )

        multiplyInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister).getRegisterValue() `should be equal to` 200.W
    }

    @Test
    internal fun `running multiply instructions using registers reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.RA
        val registerAddress2 = 2.RA
        val registerAddress3 = 3.RA
        val registerAddress4 = 4.RA

        registerArray.setValueAtRegister(registerAddress1, 10.W)
        registerArray.setValueAtRegister(registerAddress2, 2.W)

        val multiply1 = Multiply.of(
            DestinationRegister(registerAddress3),
            SourceRegister(registerAddress1),
            SourceRegister(registerAddress2)
        )
        val multiply2 = Multiply.of(
            DestinationRegister(registerAddress4),
            SourceRegister(registerAddress3),
            SourceRegister(registerAddress2)
        )

        val instructions: List<Instruction> = listOf(
            multiply1,
            multiply2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 20.W

        registerArray.getRegisterAt(registerAddress4).getRegisterValue() `should be equal to` 40.W
    }
}
