package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.sub.SubRegister
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class SubRegisterInstructionParserTest {

    @Test
    internal fun `creates correct sub instruction`() {
        val instructionString = "SUB R3, R1, R2"

        val subRegisterInstruction = SubRegisterInstructionParser(instructionString).parse()

        subRegisterInstruction `should be instance of` SubRegister::class

        subRegisterInstruction as SubRegister

        subRegisterInstruction.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_3)
        subRegisterInstruction.sourceRegister1 `should be equal to` SourceRegister(RegisterAddress.REGISTER_1)
        subRegisterInstruction.sourceRegister2 `should be equal to` SourceRegister(RegisterAddress.REGISTER_2)
    }
}
