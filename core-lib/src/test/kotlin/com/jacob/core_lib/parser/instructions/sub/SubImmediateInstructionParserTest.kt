package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.*
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.instructions.sub.SubImmediate
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class SubImmediateInstructionParserTest {
    @Test
    internal fun `creates correct sub instruction for positive decimal immediate values`() {
        val instructionString = InstructionString("SUB R3, R1, #1")

        val instruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromDec).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this as SubImmediate
            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
            immediateValue `should be equal to` 1.I
        }
    }

    @Test
    internal fun `creates correct sub instruction for negative decimal immediate values`() {
        val instructionString = InstructionString("SUB R3, R1, #-1")

        val instruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromDec).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` SubImmediate::class
            this as SubImmediate
            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
            immediateValue `should be equal to` (-1).I
        }
    }

    @Test
    internal fun `creates correct sub instruction for hexadecimal immediate values without letters`() {
        val instructionString = InstructionString("SUB R3, R1, #0X123")

        val instruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromHex).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` SubImmediate::class
            this as SubImmediate
            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
            immediateValue `should be equal to` 291.I
        }
    }

    @Test
    internal fun `creates correct sub instruction for hexadecimal immediate values with letters`() {
        val instructionString = InstructionString("SUB R3, R1, #0X1A2FC3")

        val instruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromHex).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` SubImmediate::class
            this as SubImmediate
            destinationRegister `should be equal to` 3.DR
            sourceRegister `should be equal to` 1.SR
            immediateValue `should be equal to` 1716163.I
        }
    }

    @Test
    internal fun `throws exception when given invalid hexadecimal value`() {
        val instructionString = InstructionString("SUB R1, R2, #0XABCEFGHI")
        invoking {
            SubImmediateInstructionParser(
                instructionString,
                ShiftOperation.None,
                String::immediateFromHex
            ).parse()
        } `should throw` NumberFormatException::class
    }
}
