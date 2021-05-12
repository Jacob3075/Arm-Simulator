package com.jacob.core_lib.parser.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.RegisterAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.move.MoveRegister
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class MoveRegisterInstructionParserTest {

    @Test
    internal fun `returns move register instruction parser`() {
        val instructionString = "MOV R1, R2"

        val moveInstructionParser = MoveInstructionParser.from(instructionString)

        moveInstructionParser `should be instance of` MoveRegister::class

        moveInstructionParser as MoveRegister

        moveInstructionParser.destinationRegister `should be equal to` DestinationRegister(RegisterAddress.REGISTER_1)
        moveInstructionParser.sourceRegister `should be equal to` SourceRegister(RegisterAddress.REGISTER_2)
    }

}
