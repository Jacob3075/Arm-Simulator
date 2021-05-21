package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.invoking
import org.junit.jupiter.api.Test

internal class AddImmediateInstructionParserTest {

    @Test
    internal fun `creates correct add instruction for positive decimal immediate values`() {
        val instructionString = "ADD R3, R1, #2"

        val addImmediateInstruction =
            AddImmediateInstructionParser(instructionString, String::immediateFromDec).invoke()

        addImmediateInstruction `should be instance of` AddImmediate::class

        addImmediateInstruction as AddImmediate

        addImmediateInstruction.immediateValue `should be equal to` ImmediateValue(2)
        addImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        addImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
    }

    @Test
    internal fun `creates correct add instruction for negative decimal immediate values`() {
        val instructionString = "ADD R3, R1, #-2"

        val addImmediateInstruction =
            AddImmediateInstructionParser(instructionString, String::immediateFromDec).invoke()

        addImmediateInstruction `should be instance of` AddImmediate::class

        addImmediateInstruction as AddImmediate

        addImmediateInstruction.immediateValue `should be equal to` ImmediateValue(-2)
        addImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        addImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
    }

    @Test
    internal fun `creates correct add instruction for hexadecimal immediate values without letters`() {
        val instructionString = "ADD R3, R1, #0X123"

        val instruction =
            AddImmediateInstructionParser(instructionString, String::immediateFromHex).invoke()

        instruction `should be instance of` AddImmediate::class

        instruction as AddImmediate

        instruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        instruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        instruction.immediateValue `should be equal to` 291.I
    }

    @Test
    internal fun `creates correct add instruction for hexadecimal immediate values with letters`() {
        val instructionString = "ADD R3, R1, #0X1A2FC3"

        val instruction =
            AddImmediateInstructionParser(instructionString, String::immediateFromHex).invoke()

        instruction `should be instance of` AddImmediate::class

        instruction as AddImmediate

        instruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        instruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        instruction.immediateValue `should be equal to` 1716163.I
    }

    @Test
    internal fun `throws exception when given invalid hexadecimal value`() {
        val instructionString = "ADD R1, R2, #0XABCEFGHI"
        invoking {
            AddImmediateInstructionParser(
                instructionString,
                String::immediateFromHex
            ).invoke()
        } `should throw` NumberFormatException::class
    }
}
