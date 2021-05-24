package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.instructions.shift.ShiftOperation.None
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class AddRegisterInstructionParserTest {

    @Test
    internal fun `creates correct add instruction`() {
        val instructionString = "ADD R3, R1, R2"

        val addRegisterInstruction = AddRegisterInstructionParser(instructionString, None).parse()

        addRegisterInstruction `should be instance of` AddRegister::class

        addRegisterInstruction as AddRegister

        addRegisterInstruction.destinationRegister `should be equal to` DestinationRegister(3.RA)
        addRegisterInstruction.sourceRegister1 `should be equal to` SourceRegister(1.RA)
        addRegisterInstruction.sourceRegister2 `should be equal to` SourceRegister(2.RA)

    }

}
