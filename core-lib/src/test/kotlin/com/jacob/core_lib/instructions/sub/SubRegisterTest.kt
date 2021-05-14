package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.createSubInstruction
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class SubRegisterTest {
    @Test
    internal fun `can create new sub instruction with 2 source registers`() {
        val sourceRegister1 = RegisterAddress.REGISTER_0
        val sourceRegister2 = RegisterAddress.REGISTER_0
        val destinationRegister = RegisterAddress.REGISTER_2

        val subInstruction = createSubInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        subInstruction.shouldNotBeNull()
        subInstruction `should be instance of` SubRegister::class
        subInstruction `should be instance of` Sub::class
        subInstruction `should be instance of` Instruction::class
    }

    @Test
    internal fun `executing sub instruction with register values reads and updates the correct registers`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val variables = mockk<List<Variable>>()
        val registerArray = RegisterArray()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        registerArray.setValueAtRegister(RegisterAddress.REGISTER_0, Word(20))
        registerArray.setValueAtRegister(RegisterAddress.REGISTER_1, Word(10))

        val sourceRegister1 = RegisterAddress.REGISTER_0
        val sourceRegister2 = RegisterAddress.REGISTER_1
        val destinationRegister = RegisterAddress.REGISTER_2

        val subInstruction = createSubInstruction(destinationRegister, sourceRegister1, sourceRegister2)

        subInstruction.execute(executionEnvironment)

        registerArray.getRegisterAt(destinationRegister)
            .getRegisterValue() `should be equal to` Word(10)
    }


    @Test
    internal fun `running sub instructions using registers reads and updates the correct registers`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()
        val variables = listOf<ParsedData>()

        val registerAddress1 = RegisterAddress.REGISTER_1
        val registerAddress2 = RegisterAddress.REGISTER_2
        val registerAddress3 = RegisterAddress.REGISTER_3
        val registerAddress4 = RegisterAddress.REGISTER_4

        registerArray.setValueAtRegister(registerAddress1, Word(30))
        registerArray.setValueAtRegister(registerAddress2, Word(10))

        val sub1 = createSubInstruction(registerAddress3, registerAddress1, registerAddress2)
        val sub2 = createSubInstruction(registerAddress4, registerAddress3, registerAddress2)

        val instructions: List<Instruction> = listOf(
            sub1,
            sub2,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1)
            .getRegisterValue() `should be equal to` Word(30)

        registerArray.getRegisterAt(registerAddress2)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(registerAddress3)
            .getRegisterValue() `should be equal to` Word(20)

        registerArray.getRegisterAt(registerAddress4)
            .getRegisterValue() `should be equal to` Word(10)

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
