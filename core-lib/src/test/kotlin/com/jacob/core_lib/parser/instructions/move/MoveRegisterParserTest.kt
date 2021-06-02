package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddresses
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.move.MoveRegister
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class MoveRegisterParserTest {

    @Test
    internal fun `returns move register instruction parser`() {
        val instructionString = InstructionString("MOV R1, R2")

        val moveInstructionParser = MoveInstructionParser.from(instructionString) as Always

        moveInstructionParser.instruction.apply {
            this `should be instance of` MoveRegister::class
            this as MoveRegister

            destinationRegister `should be equal to` DestinationRegister(RegisterAddresses.REGISTER_1)
            sourceRegister `should be equal to` SourceRegister(RegisterAddresses.REGISTER_2)
        }
    }
}
