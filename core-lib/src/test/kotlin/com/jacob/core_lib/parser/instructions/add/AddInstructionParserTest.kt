package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.instructions.conditionals.Conditional
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

// TODO: TESTS WITH CONDITIONALS
internal class AddInstructionParserTest {
    @Test
    internal fun `returns add register instruction parser`() {
        val instructionString = InstructionString("ADD R1, R2, R3")

        val instruction = AddInstructionParser.from(instructionString)

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddRegister::class
    }

    @Test
    internal fun `returns add immediate instruction parser`() {
        val instructionString = InstructionString("ADD R1, R2, #3")

        val instruction = AddInstructionParser.from(instructionString)

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddImmediate::class
    }

    @Test
    internal fun `returns add register instruction parser with shift operation 1`() {
        val instructionString = InstructionString("ADD R1, R2, R3, LSL #2")

        val instruction = AddInstructionParser.from(instructionString)

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddRegister::class
    }

    @Test
    internal fun `returns add register instruction parser with shift operation 2`() {
        val instructionString = InstructionString("ADD R1, R2, R11, LSL #11")

        val instruction = AddInstructionParser.from(instructionString)

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddRegister::class
    }

}
