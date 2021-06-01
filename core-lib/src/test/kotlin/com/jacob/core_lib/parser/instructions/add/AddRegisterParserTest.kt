package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.LS
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.conditionals.Equal
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class AddRegisterParserTest {
    @Test
    internal fun `creates correct add instruction`() {
        val instructionString = InstructionString("ADD R3, R1, R12")

        val instruction = AddRegisterParser(instructionString).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddRegister::class
            this as AddRegister

            destinationRegister `should be equal to` 3.DR
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 12.SR
        }
    }

    @Test
    internal fun `creates correct instruction when conditional is present`() {
        val instructionString = InstructionString("ADDEQ R3, R1, R2")

        val instruction =
            AddRegisterParser(instructionString).parse()

        instruction `should be instance of` Equal::class
        instruction as Equal

        instruction.instruction.apply {
            this `should be instance of` AddRegister::class
            this as AddRegister

            destinationRegister `should be equal to` 3.DR
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 2.SR
        }
    }

    @Test
    internal fun `creates correct instruction when shift operation is present`() {
        val instructionString = InstructionString("ADD R3, R1, R2, LSL #3")

        val instruction =
            AddRegisterParser(instructionString).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddRegister::class
            this as AddRegister

            destinationRegister `should be equal to` 3.DR
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 2.SR
            shiftOperation `should be equal to` 3.LS
        }
    }
}
