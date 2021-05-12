package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.add.AddImmediate
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class AddImmediateInstructionParserTest {

    @Test
    internal fun `creates correct add instruction for positive immediate values`() {
        val instructionString = "ADD R3, R1, #2"

        val addImmediateInstruction = AddImmediateInstructionParser(instructionString).invoke()

        addImmediateInstruction `should be instance of` AddImmediate::class

        addImmediateInstruction as AddImmediate

        addImmediateInstruction.immediateValue `should be equal to` ImmediateValue(2)
        addImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        addImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
    }

    @Test
    internal fun `creates correct add instruction for negative immediate values`() {
        val instructionString = "ADD R3, R1, #-2"

        val addImmediateInstruction = AddImmediateInstructionParser(instructionString).invoke()

        addImmediateInstruction `should be instance of` AddImmediate::class

        addImmediateInstruction as AddImmediate

        addImmediateInstruction.immediateValue `should be equal to` ImmediateValue(-2)
        addImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        addImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
    }
}
