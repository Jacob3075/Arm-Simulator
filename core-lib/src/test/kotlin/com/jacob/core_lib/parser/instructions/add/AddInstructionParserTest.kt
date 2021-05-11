package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.instructions.add.AddRegister
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class AddInstructionParserTest {
    @Test
    internal fun `returns add register instruction parser`() {
        val instructionString = "ADD R1, R2, R3"

        val addInstructionParser = AddInstructionParser.from(instructionString)

        addInstructionParser.shouldNotBeNull()
        addInstructionParser `should be instance of` Instruction::class
        addInstructionParser `should be instance of` AddRegister::class
    }

    @Test
    internal fun `returns add immediate instruction parser`() {
        val instructionString = "ADD R1, R2, #3"

        val addInstructionParser = AddInstructionParser.from(instructionString)

        addInstructionParser.shouldNotBeNull()
        addInstructionParser `should be instance of` Instruction::class
        addInstructionParser `should be instance of` AddImmediate::class
    }
}
