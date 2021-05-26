package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.*
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.getExecutionEnvironment
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.parser.data.ParsedData
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
        val index1 = 5
        val index2 = 16
        val labelName1 = "LABEL1"
        val labelName2 = "LABEL2"
        val labels = listOf(Label(labelName1, index1), Label(labelName2, index2))
        val registerArray = RegisterArray()

        val executionEnvironment = getExecutionEnvironment(registerArray = registerArray, labels = labels)

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
        val variables = listOf<ParsedData>()

        val registerAddress1 = 1.DR
        val registerAddress2 = 2.DR
        val registerAddress3 = 6.DR

        val labelName = "LABEL1"

        val move1 = Move.of(registerAddress1, 10.I)
        val move2 = Move.of(registerAddress2, 20.I)
        val move3 = Move.of(registerAddress3, 30.I)
        val branch = Branch(labelName)
        val label = Label(labelName)

        val instructions: List<Instruction> = listOf(
            move1,
            branch,
            move2,
            label,
            move3,
        )
        val program = Program(instructions, variables)

        val core = Core(memoryArray, registerArray, program)

        core.runProgram()

        registerArray.getRegisterAt(registerAddress1).getRegisterValue() `should be equal to` 10.W
        registerArray.getRegisterAt(registerAddress3).getRegisterValue() `should be equal to` 30.W
        registerArray.getRegisterAt(2.SR).getRegisterValue() `should be equal to` 0.W
    }
}
