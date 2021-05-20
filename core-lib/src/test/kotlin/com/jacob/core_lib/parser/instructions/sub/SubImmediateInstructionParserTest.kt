package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.instructions.sub.SubImmediate
import com.jacob.core_lib.word.ImmediateValue
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class SubImmediateInstructionParserTest {

    @Test
    internal fun `creates correct sub instruction for positive immediate values`() {
        val instructionString = "SUB R3, R1, #1"

        val subImmediateInstruction =
            SubImmediateInstructionParser(instructionString, String::immediateFromDec).invoke()

        subImmediateInstruction `should be instance of` SubImmediate::class

        subImmediateInstruction as SubImmediate

        subImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subImmediateInstruction.immediateValue `should be equal to` ImmediateValue(1)
    }

    @Test
    internal fun `creates correct sub instruction for negative immediate values`() {
        val instructionString = "SUB R3, R1, #-1"

        val subImmediateInstruction =
            SubImmediateInstructionParser(instructionString, String::immediateFromDec).invoke()

        subImmediateInstruction `should be instance of` SubImmediate::class

        subImmediateInstruction as SubImmediate

        subImmediateInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subImmediateInstruction.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subImmediateInstruction.immediateValue `should be equal to` ImmediateValue(-1)
    }
}
