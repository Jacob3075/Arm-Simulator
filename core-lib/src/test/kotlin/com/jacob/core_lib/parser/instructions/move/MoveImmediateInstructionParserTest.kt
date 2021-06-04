package com.jacob.core_lib.parser.instructions.move

import arrow.core.getOrElse
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.move.MoveImmediate
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class MoveImmediateInstructionParserTest {
    @Test
    internal fun `returns move immediate instruction for positive decimal immediate values`() {
        val instructionString = InstructionString("MOV R1, #2")

        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!! as Always

        instruction.instruction.apply {
            this `should be instance of` MoveImmediate::class
            this as MoveImmediate

            immediateValue `should be equal to` 2.I
            destinationRegister `should be equal to` DestinationRegister(RegisterAddresses.REGISTER_1)
        }
    }

    @Test
    internal fun `returns move immediate instruction for negative decimal immediate values`() {
        val instructionString = InstructionString("MOV R1, #-2")

        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!! as Always

        instruction.instruction.apply {
            this `should be instance of` MoveImmediate::class
            this as MoveImmediate

            immediateValue `should be equal to` (-2).I
            destinationRegister `should be equal to` DestinationRegister(1.RA)
        }
    }

    @Test
    internal fun `returns move immediate instruction for hexadecimal immediate values without letters`() {
        val instructionString = InstructionString("MOV R1, #0X123")

        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!! as Always

        instruction.instruction.apply {
            this `should be instance of` MoveImmediate::class
            this as MoveImmediate

            immediateValue `should be equal to` 291.I
            destinationRegister `should be equal to` DestinationRegister(1.RA)
        }
    }

    @Test
    internal fun `returns move immediate instruction for hexadecimal immediate values with letters`() {
        val instructionString = InstructionString("MOV R1, #0X1A2FC3")

        val instruction = MoveInstructionParser.from(instructionString).getOrElse { null }!! as Always

        instruction.instruction.apply {
            this `should be instance of` MoveImmediate::class
            this as MoveImmediate

            immediateValue `should be equal to` 1716163.I
            destinationRegister `should be equal to` DestinationRegister(1.RA)
        }
    }
}
