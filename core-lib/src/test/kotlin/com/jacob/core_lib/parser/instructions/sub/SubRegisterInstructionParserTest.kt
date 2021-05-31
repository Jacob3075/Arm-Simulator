package com.jacob.core_lib.parser.instructions.sub

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.instructions.sub.SubRegister
import com.jacob.core_lib.parser.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class SubRegisterInstructionParserTest {
    @Test
    internal fun `creates correct sub instruction`() {
        val instructionString = InstructionString("SUB R3, R1, R12")

        val instruction = SubRegisterInstructionParser(instructionString).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` SubRegister::class
            this as SubRegister
            destinationRegister `should be equal to` 3.DR
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 12.SR
        }
    }
}
