package com.jacob.core_lib.parser.move

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.move.MoveImmediate
import com.jacob.core_lib.instructions.move.MoveRegister
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveImmediateInstructionParserTest {

    @Test
    internal fun `returns move register instruction parser`() {
        val instructionString = "MOV R1, R2"

        val moveInstructionParser = MoveInstructionParser.from(instructionString)

        moveInstructionParser.shouldNotBeNull()
        moveInstructionParser `should be instance of` Instruction::class
        moveInstructionParser `should be instance of` MoveRegister::class
    }

    @Test
    internal fun `returns move immediate instruction parser`() {
        val instructionString = "MOV R1, #2"

        val moveInstructionParser = MoveInstructionParser.from(instructionString)

        moveInstructionParser.shouldNotBeNull()
        moveInstructionParser `should be instance of` Instruction::class
        moveInstructionParser `should be instance of` MoveImmediate::class
    }
}
