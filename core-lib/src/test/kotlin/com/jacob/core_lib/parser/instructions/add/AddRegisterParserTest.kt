package com.jacob.core_lib.parser.instructions.add

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.add.AddRegister
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class AddRegisterParserTest {

    @Test
    internal fun `creates correct add instruction`() {
        val instructionString = InstructionString("ADD R3, R1, R2")

        val instruction = AddRegisterParser(instructionString).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` AddRegister::class
            this as AddRegister

            destinationRegister `should be equal to` 3.DR
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 2.SR
        }
    }
}
