package com.jacob.core_lib.parser.instructions.branch

import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class BranchInstructionParserTest {
    @Test
    internal fun `creates correct instruction with single letter label name`() {
        val instructionString = InstructionString("B A")
        val branch = BranchInstructionParser.from(instructionString)

        branch.labelName `should be equal to` "A"
    }

    @Test
    internal fun `creates correct instruction with multiple letter label name`() {
        val instructionString = InstructionString("B ABC_A-D1_A")
        val branch = BranchInstructionParser.from(instructionString)

        branch.labelName `should be equal to` "ABC_A-D1_A"
    }

}
