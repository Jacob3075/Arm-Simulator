package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
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
        val index1 = 5
        val index2 = 16
        val labelName1 = "LABEL1"
        val labelName2 = "LABEL2"
        val labels = listOf(Label(labelName1, index1), Label(labelName2, index2))
        val registerArray = RegisterArray()

        val branchInstruction1 = Branch(labelName1)
        val branchInstruction2 = Branch(labelName2)

        branchInstruction1.execute(memoryArray, registerArray, labels)
        registerArray.programCounter.nextInstructionAddress `should be equal to` index1

        branchInstruction2.execute(memoryArray, registerArray, labels)
        registerArray.programCounter.nextInstructionAddress `should be equal to` index2

        branchInstruction1.execute(memoryArray, registerArray, labels)
        registerArray.programCounter.nextInstructionAddress `should be equal to` index1
    }
}
