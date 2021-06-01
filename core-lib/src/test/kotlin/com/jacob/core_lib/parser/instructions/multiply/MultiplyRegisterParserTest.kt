package com.jacob.core_lib.parser.instructions.multiply

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.multipy.MultiplyRegister
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class MultiplyRegisterParserTest {
    @Test
    internal fun `creates correct multiply instruction`() {
        val instructionString = InstructionString("MUL R3, R1, R2")

        val multiplyRegisterInstruction = MultiplyRegisterParser(instructionString).parse()

        multiplyRegisterInstruction `should be instance of` MultiplyRegister::class

        multiplyRegisterInstruction as MultiplyRegister

        multiplyRegisterInstruction.destinationRegister `should be equal to` DestinationRegister(3.RA)
        multiplyRegisterInstruction.sourceRegister1 `should be equal to` SourceRegister(1.RA)
        multiplyRegisterInstruction.sourceRegister2 `should be equal to` SourceRegister(2.RA)
    }
}
