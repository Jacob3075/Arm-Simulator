package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.instructions.conditionals.Conditional
import com.jacob.core_lib.instructions.shift.ShiftOperation.None
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class AddImmediateInstructionParserTest {

    @Test
    internal fun `creates correct add instruction for positive decimal immediate values`() {
        val instructionString = InstructionString("ADD R3, R1, #2")

        val instruction =
            AddImmediateInstructionParser(instructionString, None, String::immediateFromDec).parse()

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddImmediate::class

        (instruction.instruction as AddImmediate).immediateValue `should be equal to` 2.I
        (instruction.instruction as AddImmediate).destinationRegister `should be equal to` DestinationRegister(3.RA)
        (instruction.instruction as AddImmediate).sourceRegister `should be equal to` SourceRegister(1.RA)
    }

    @Test
    internal fun `creates correct add instruction for negative decimal immediate values`() {
        val instructionString = InstructionString("ADD R3, R1, #-2")

        val instruction =
            AddImmediateInstructionParser(instructionString, None, String::immediateFromDec).parse()

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddImmediate::class

        (instruction.instruction as AddImmediate).immediateValue `should be equal to` (-2).I
        (instruction.instruction as AddImmediate).destinationRegister `should be equal to` DestinationRegister(3.RA)
        (instruction.instruction as AddImmediate).sourceRegister `should be equal to` SourceRegister(1.RA)
    }

    @Test
    internal fun `creates correct add instruction for hexadecimal immediate values without letters`() {
        val instructionString = InstructionString("ADD R3, R1, #0X123")

        val instruction =
            AddImmediateInstructionParser(instructionString, None, String::immediateFromHex).parse()

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddImmediate::class

        (instruction.instruction as AddImmediate).destinationRegister `should be equal to` DestinationRegister(3.RA)
        (instruction.instruction as AddImmediate).sourceRegister `should be equal to` SourceRegister(1.RA)
        (instruction.instruction as AddImmediate).immediateValue `should be equal to` 291.I
    }

    @Test
    internal fun `creates correct add instruction for hexadecimal immediate values with letters`() {
        val instructionString = InstructionString("ADD R3, R1, #0X1A2FC3")

        val instruction =
            AddImmediateInstructionParser(instructionString, None, String::immediateFromHex).parse()

        instruction `should be instance of` Conditional::class

        instruction as Conditional

        instruction.instruction `should be instance of` AddImmediate::class

        (instruction.instruction as AddImmediate).destinationRegister `should be equal to` DestinationRegister(3.RA)
        (instruction.instruction as AddImmediate).sourceRegister `should be equal to` SourceRegister(1.RA)
        (instruction.instruction as AddImmediate).immediateValue `should be equal to` 1716163.I
    }

    @Test
    internal fun `throws exception when given invalid hexadecimal value`() {
        val instructionString = InstructionString("ADD R1, R2, #0XABCEFGHI")
        invoking {
            AddImmediateInstructionParser(
                instructionString,
                None,
                String::immediateFromHex
            ).parse()
        } `should throw` NumberFormatException::class
    }
}
