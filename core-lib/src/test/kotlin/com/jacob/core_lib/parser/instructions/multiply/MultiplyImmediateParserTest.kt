package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.multipy.MultiplyImmediate
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class MultiplyImmediateParserTest {
    @Test
    internal fun `creates correct multiply instruction for positive decimal immediate values`() {
        val instructionString = InstructionString("MUL R3, R1, #2")

        val instruction =
            MultiplyImmediateParser(instructionString, String::immediateFromDec).parse() as Always

        instruction.instruction.apply {
            this `should be instance of` MultiplyImmediate::class
            this as MultiplyImmediate

            immediateValue `should be equal to` 2.I
            destinationRegister `should be equal to` DestinationRegister(3.RA)
            sourceRegister `should be equal to` SourceRegister(1.RA)
        }
    }

    @Test
    internal fun `creates correct multiply instruction for negative decimal immediate values`() {
        val instructionString = InstructionString("MUL R3, R1, #-2")

        val multiplyImmediateInstruction =
            MultiplyImmediateParser(instructionString, String::immediateFromDec).parse() as Always

        multiplyImmediateInstruction.instruction.apply {
            this `should be instance of` MultiplyImmediate::class
            this as MultiplyImmediate

            immediateValue `should be equal to` (-2).I
            destinationRegister `should be equal to` DestinationRegister(3.RA)
            sourceRegister `should be equal to` SourceRegister(1.RA)
        }
    }

    @Test
    internal fun `creates correct multiply instruction for hexadecimal immediate values without letters`() {
        val instructionString = InstructionString("MUL R3, R1, #0X123")

        val instruction =
            MultiplyImmediateParser(instructionString, String::immediateFromHex).parse() as Always

        instruction.instruction.apply {
            this `should be instance of` MultiplyImmediate::class
            this as MultiplyImmediate

            destinationRegister `should be equal to` DestinationRegister(3.RA)
            sourceRegister `should be equal to` SourceRegister(1.RA)
            immediateValue `should be equal to` 291.I
        }
    }

    @Test
    internal fun `creates correct multiply instruction for hexadecimal immediate values with letters`() {
        val instructionString = InstructionString("MUL R3, R1, #0X1A2FC3")

        val instruction =
            MultiplyImmediateParser(instructionString, String::immediateFromHex).parse() as Always

        instruction.instruction.apply {
            this `should be instance of` MultiplyImmediate::class
            this as MultiplyImmediate

            destinationRegister `should be equal to` DestinationRegister(3.RA)
            sourceRegister `should be equal to` SourceRegister(1.RA)
            immediateValue `should be equal to` 1716163.I
        }
    }

    @Test
    internal fun `throws exception when given invalid hexadecimal value`() {
        val instructionString = InstructionString("MUL R1, R2, #0XABCEFGHI")
        invoking {
            MultiplyImmediateParser(
                instructionString,
                String::immediateFromHex
            ).parse()
        } `should throw` NumberFormatException::class
    }
}
