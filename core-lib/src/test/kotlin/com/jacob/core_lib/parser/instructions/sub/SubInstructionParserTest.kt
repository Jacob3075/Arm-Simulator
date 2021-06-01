package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.sub.SubImmediate
import com.jacob.core_lib.instructions.sub.SubRegister
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class SubInstructionParserTest {
    @Test
    internal fun `returns sub register instruction parser`() {
        val instructionString = InstructionString("SUB R1, R2, R3")

        val instruction = SubInstructionParser.from(instructionString)

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` SubRegister::class
    }

    @Test
    internal fun `returns sub immediate instruction parser`() {
        val instructionString = InstructionString("SUB R1, R2, #3")

        val instruction = SubInstructionParser.from(instructionString)

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` SubImmediate::class
    }

    @Test
    internal fun `returns sub immediate instruction parser with left shifts`() {
        val instructionString = InstructionString("SUB R1, R2, #3 LSL #3")

        val instruction = SubInstructionParser.from(instructionString)

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` SubImmediate::class
    }

    @Test
    internal fun `returns sub immediate instruction parser with right shifts`() {
        val instructionString = InstructionString("SUB R1, R2, #3 LSR #3")

        val instruction = SubInstructionParser.from(instructionString)

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` SubImmediate::class
    }
}
