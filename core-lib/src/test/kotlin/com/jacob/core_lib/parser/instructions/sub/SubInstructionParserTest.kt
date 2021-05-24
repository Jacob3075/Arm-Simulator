package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.sub.SubImmediate
import com.jacob.core_lib.instructions.sub.SubRegister
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class SubInstructionParserTest {
    @Test
    internal fun `returns sub register instruction parser`() {
        val instructionString = "SUB R1, R2, R3"

        val subInstructionParser = SubInstructionParser.from(instructionString)

        subInstructionParser.shouldNotBeNull()
        subInstructionParser `should be instance of` Instruction::class
        subInstructionParser `should be instance of` SubRegister::class
    }

    @Test
    internal fun `returns sub immediate instruction parser`() {
        val instructionString = "SUB R1, R2, #3"

        val subInstructionParser = SubInstructionParser.from(instructionString)

        subInstructionParser.shouldNotBeNull()
        subInstructionParser `should be instance of` Instruction::class
        subInstructionParser `should be instance of` SubImmediate::class
    }

    @Test
    internal fun `returns sub immediate instruction parser with left shifts`() {
        val instructionString = "SUB R1, R2, #3 LSL #3"

        val subInstructionParser = SubInstructionParser.from(instructionString)

        subInstructionParser.shouldNotBeNull()
        subInstructionParser `should be instance of` Instruction::class
        subInstructionParser `should be instance of` SubImmediate::class
    }

    @Test
    internal fun `returns sub immediate instruction parser with right shifts`() {
        val instructionString = "SUB R1, R2, #3 LSR #3"

        val subInstructionParser = SubInstructionParser.from(instructionString)

        subInstructionParser.shouldNotBeNull()
        subInstructionParser `should be instance of` Instruction::class
        subInstructionParser `should be instance of` SubImmediate::class
    }
}
