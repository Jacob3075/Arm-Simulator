package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.instructions.move.MoveImmediate
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class MoveImmediateInstructionParserTest {
    @Test
    internal fun `returns move immediate instruction for positive immediate values`() {
        val instructionString = "MOV R1, #2"

        val moveInstructionParser = MoveInstructionParser.from(instructionString)

        moveInstructionParser `should be instance of` MoveImmediate::class

        moveInstructionParser as MoveImmediate

        moveInstructionParser.immediateValue `should be equal to` ImmediateValue(2)
        moveInstructionParser.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_1)
    }

    @Test
    internal fun `returns move immediate instruction for negative immediate values`() {
        val instructionString = "MOV R1, #-2"

        val moveInstructionParser = MoveInstructionParser.from(instructionString)

        moveInstructionParser `should be instance of` MoveImmediate::class

        moveInstructionParser as MoveImmediate

        moveInstructionParser.immediateValue `should be equal to` ImmediateValue(-2)
        moveInstructionParser.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_1)
    }
}
