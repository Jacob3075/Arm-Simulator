package com.jacob.core_lib.parser.instructions.move

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.move.MoveImmediate
import com.jacob.core_lib.instructions.move.MoveRegister
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Test

internal class MoveInstructionParserTest {
    @Test
    internal fun `creates correct instruction when given postive immediate value`() {
        val instructionString = InstructionString("MOV R1, #5")
        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` MoveImmediate::class
    }

    @Test
    internal fun `creates correct instruction when given negative immediate value`() {
        val instructionString = InstructionString("MOV R1, #-5")
        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` MoveImmediate::class
    }

    @Test
    internal fun `creates correct instruction when given register value`() {
        val instructionString = InstructionString("MOV R1, R5")
        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` MoveRegister::class
    }

    @Test
    internal fun `throws exception if invalid register is given`() {
        val instructionString = InstructionString("MOV R1, R20")
        MoveInstructionParser.from(instructionString).isInvalid.shouldBeTrue()

    }
}
