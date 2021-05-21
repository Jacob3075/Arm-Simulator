package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.move.MoveImmediate
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class MoveImmediateInstructionParserTest {
    @Test
    internal fun `returns move immediate instruction for positive decimal immediate values`() {
        val instructionString = "MOV R1, #2"

        val instruction = MoveInstructionParser.from(instructionString)

        instruction `should be instance of` MoveImmediate::class

        instruction as MoveImmediate

        instruction.immediateValue `should be equal to` 2.I
        instruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_1)
    }

    @Test
    internal fun `returns move immediate instruction for negative decimal immediate values`() {
        val instructionString = "MOV R1, #-2"

        val instruction = MoveInstructionParser.from(instructionString)

        instruction `should be instance of` MoveImmediate::class

        instruction as MoveImmediate

        instruction.immediateValue `should be equal to` (-2).I
        instruction.destinationRegister `should be equal to` DestinationRegister(1.RA)
    }

    @Test
    internal fun `returns move immediate instruction for hexadecimal immediate values without letters`() {
        val instructionString = "MOV R1, #0X123"

        val instruction = MoveInstructionParser.from(instructionString)

        instruction `should be instance of` MoveImmediate::class

        instruction as MoveImmediate

        instruction.immediateValue `should be equal to` 291.I
        instruction.destinationRegister `should be equal to` DestinationRegister(1.RA)
    }

    @Test
    internal fun `returns move immediate instruction for hexadecimal immediate values with letters`() {
        val instructionString = "MOV R1, #0X1A2FC3"

        val instruction = MoveInstructionParser.from(instructionString)

        instruction `should be instance of` MoveImmediate::class

        instruction as MoveImmediate

        instruction.immediateValue `should be equal to` 1716163.I
        instruction.destinationRegister `should be equal to` DestinationRegister(1.RA)
    }
}
