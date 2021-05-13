package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.core.*
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.createBranchInstruction
import com.jacob.core_lib.createLabelInstruction
import com.jacob.core_lib.createMoveInstruction
import com.jacob.core_lib.word.Word
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class BranchTest {
    @Test
    internal fun `can create branch instruction`() {
        val branchInstruction = Branch("LABEL")

        branchInstruction.shouldNotBeNull()
    }

    @Test
    internal fun `running branch instruction should update program counter correctly`() {
        val memoryArray = mockk<MemoryArray>()
        val variables = mockk<List<Variable>>()
        val index1 = 5
        val index2 = 16
        val labelName1 = "LABEL1"
        val labelName2 = "LABEL2"
        val labels = listOf(Label(labelName1, index1), Label(labelName2, index2))
        val registerArray = RegisterArray()

        val executionEnvironment = ExecutionEnvironment(
            registerArray = registerArray,
            memoryArray = memoryArray,
            labels = labels,
            variables = variables
        )

        val branchInstruction1 = Branch(labelName1)
        val branchInstruction2 = Branch(labelName2)

        branchInstruction1.execute(executionEnvironment)
        registerArray.programCounter.nextInstructionAddress `should be equal to` index1

        branchInstruction2.execute(executionEnvironment)
        registerArray.programCounter.nextInstructionAddress `should be equal to` index2

        branchInstruction1.execute(executionEnvironment)
        registerArray.programCounter.nextInstructionAddress `should be equal to` index1
    }

    @Test
    internal fun `running branch instructions jump to the correct point`() {
        val memoryArray = MemoryArray()
        val registerArray = RegisterArray()

        val registerAddress1 = RegisterAddress.REGISTER_1
        val registerAddress2 = RegisterAddress.REGISTER_2
        val registerAddress3 = RegisterAddress.REGISTER_6

        val labelName = "LABEL1"

        val move1 = createMoveInstruction(registerAddress1, 10)
        val move2 = createMoveInstruction(registerAddress2, 20)
        val move3 = createMoveInstruction(registerAddress3, 30)
        val branch = createBranchInstruction(labelName)
        val label = createLabelInstruction(labelName)

        val instructions: List<Instruction> = listOf(
            move1,
            branch,
            move2,
            label,
            move3,
        )
        val program = Program(instructions)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1)
            .getRegisterValue() `should be equal to` Word(10)

        registerArray.getRegisterAt(registerAddress3)
            .getRegisterValue() `should be equal to` Word(30)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_2)
            .getRegisterValue() `should be equal to` Word(0)

        registerArray.getRegisterAt(RegisterAddress.REGISTER_3).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_4).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_5).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_7).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_8).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_9).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_10).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_11).getRegisterValue() `should be equal to` Word(0)
        registerArray.getRegisterAt(RegisterAddress.REGISTER_12).getRegisterValue() `should be equal to` Word(0)
    }
}
