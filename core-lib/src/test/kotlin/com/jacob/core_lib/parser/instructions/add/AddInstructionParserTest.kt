package com.jacob.core_lib.parser.instructions.add

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class AddInstructionParserTest {
    @Test
    internal fun `returns add register instruction parser`() {
        val instructionString = InstructionString("ADD R1, R2, R3")

        val instruction = AddInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class

        instruction as Always

        instruction.instruction `should be instance of` AddRegister::class
    }

    @Test
    internal fun `returns add immediate instruction parser`() {
        val instructionString = InstructionString("ADD R1, R2, #3")

        val instruction = AddInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class

        instruction as Always

        instruction.instruction `should be instance of` AddImmediate::class
    }

    @Test
    internal fun `returns add register instruction parser with shift operation`() {
        val instructionString = InstructionString("ADD R1, R2, R3, LSL #2")

        val instruction = AddInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class

        instruction as Always

        instruction.instruction `should be instance of` AddRegister::class
    }
}
