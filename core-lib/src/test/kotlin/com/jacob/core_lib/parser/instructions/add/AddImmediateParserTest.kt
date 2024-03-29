package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.*
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class AddImmediateParserTest {
    @Test
    internal fun `creates correct add instruction for positive decimal immediate values`() {
        val instructionString = InstructionString("ADD R3, R1, #2")

        val instruction =
            AddImmediateParser(instructionString, String::immediateFromDec).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddImmediate::class
            this as AddImmediate

            immediateValue `should be equal to` 2.I
            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
        }
    }

    @Test
    internal fun `creates correct add instruction for negative decimal immediate values`() {
        val instructionString = InstructionString("ADD R3, R1, #-2")

        val instruction =
            AddImmediateParser(instructionString, String::immediateFromDec).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddImmediate::class
            this as AddImmediate

            immediateValue `should be equal to` (-2).I
            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
        }
    }

    @Test
    internal fun `creates correct add instruction for hexadecimal immediate values without letters`() {
        val instructionString = InstructionString("ADD R3, R1, #0X123")

        val instruction =
            AddImmediateParser(instructionString, String::immediateFromHex).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddImmediate::class
            this as AddImmediate

            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
            immediateValue `should be equal to` 291.I
        }
    }

    @Test
    internal fun `creates correct add instruction for hexadecimal immediate values with letters`() {
        val instructionString = InstructionString("ADD R3, R1, #0X1A2FC3")

        val instruction =
            AddImmediateParser(instructionString, String::immediateFromHex).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddImmediate::class
            this as AddImmediate

            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
            immediateValue `should be equal to` 1716163.I
        }
    }

    @Test
    internal fun `throws exception when given invalid hexadecimal value`() {
        val instructionString = InstructionString("ADD R1, R2, #0XABCEFGHI")
        invoking {
            AddImmediateParser(
                instructionString,
                String::immediateFromHex
            ).parse()
        } `should throw` NumberFormatException::class
    }
}
