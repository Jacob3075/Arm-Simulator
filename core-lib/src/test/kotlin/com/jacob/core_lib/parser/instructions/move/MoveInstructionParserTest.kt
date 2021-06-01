package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.move.MoveImmediate
import com.jacob.core_lib.instructions.move.MoveRegister
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class MoveInstructionParserTest {
    @Test
    internal fun `creates correct instruction when given postive immediate value`() {
        val instructionString = InstructionString("MOV R1, #5")
        val moveInstruction = MoveInstructionParser.from(instructionString)

        moveInstruction `should be instance of` Instruction::class
        moveInstruction `should be instance of` MoveImmediate::class
    }

    @Test
    internal fun `creates correct instruction when given negative immediate value`() {
        val instructionString = InstructionString("MOV R1, #-5")
        val moveInstruction = MoveInstructionParser.from(instructionString)

        moveInstruction `should be instance of` Instruction::class
        moveInstruction `should be instance of` MoveImmediate::class
    }

    @Test
    internal fun `creates correct instruction when given register value`() {
        val instructionString = InstructionString("MOV R1, R5")
        val moveInstruction = MoveInstructionParser.from(instructionString)

        moveInstruction `should be instance of` Instruction::class
        moveInstruction `should be instance of` MoveRegister::class
    }

    @Test
    internal fun `throws exception if invalid register is given`() {
        val instructionString = InstructionString("MOV R1, R20")
        invoking { MoveInstructionParser.from(instructionString) } `should throw` IllegalArgumentException::class

    }
}
