package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.instructions.conditionals.Conditional
import com.jacob.core_lib.instructions.shift.ShiftOperation.None
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class AddRegisterInstructionParserTest {

    @Test
    internal fun `creates correct add instruction`() {
        val instructionString = InstructionString("ADD R3, R1, R2")

        val addRegisterInstruction = AddRegisterInstructionParser(instructionString, None).parse()

        addRegisterInstruction `should be instance of` Conditional::class
        addRegisterInstruction as Conditional
        addRegisterInstruction.instruction `should be instance of` AddRegister::class

        (addRegisterInstruction.instruction as AddRegister).destinationRegister `should be equal to` DestinationRegister(
            3.RA
        )
        (addRegisterInstruction.instruction as AddRegister).sourceRegister1 `should be equal to` SourceRegister(1.RA)
        (addRegisterInstruction.instruction as AddRegister).sourceRegister2 `should be equal to` SourceRegister(2.RA)
    }
}
