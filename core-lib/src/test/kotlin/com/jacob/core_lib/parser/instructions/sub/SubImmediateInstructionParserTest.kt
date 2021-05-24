package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.instructions.sub.SubImmediate
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class SubImmediateInstructionParserTest {

    @Test
    internal fun `creates correct sub instruction for positive decimal immediate values`() {
        val instructionString = "SUB R3, R1, #1"

        val subImmediateInstruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromDec).parse()

        subImmediateInstruction `should be instance of` SubImmediate::class

        subImmediateInstruction as SubImmediate

        subImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subImmediateInstruction.immediateValue `should be equal to` 1.I
    }

    @Test
    internal fun `creates correct sub instruction for negative decimal immediate values`() {
        val instructionString = "SUB R3, R1, #-1"

        val subImmediateInstruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromDec).parse()

        subImmediateInstruction `should be instance of` SubImmediate::class

        subImmediateInstruction as SubImmediate

        subImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subImmediateInstruction.immediateValue `should be equal to` (-1).I
    }

    @Test
    internal fun `creates correct sub instruction for hexadecimal immediate values without letters`() {
        val instructionString = "SUB R3, R1, #0X123"

        val subImmediateInstruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromHex).parse()

        subImmediateInstruction `should be instance of` SubImmediate::class

        subImmediateInstruction as SubImmediate

        subImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subImmediateInstruction.immediateValue `should be equal to` 291.I
    }

    @Test
    internal fun `creates correct sub instruction for hexadecimal immediate values with letters`() {
        val instructionString = "SUB R3, R1, #0X1A2FC3"

        val subImmediateInstruction =
            SubImmediateInstructionParser(instructionString, ShiftOperation.None, String::immediateFromHex).parse()

        subImmediateInstruction `should be instance of` SubImmediate::class

        subImmediateInstruction as SubImmediate

        subImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subImmediateInstruction.immediateValue `should be equal to` 1716163.I
    }

    @Test
    internal fun `throws exception when given invalid hexadecimal value`() {
        val instructionString = "SUB R1, R2, #0XABCEFGHI"
        invoking {
            SubImmediateInstructionParser(
                instructionString,
                ShiftOperation.None,
                String::immediateFromHex
            ).parse()
        } `should throw` NumberFormatException::class
    }
}
