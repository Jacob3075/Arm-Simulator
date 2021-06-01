package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.SR
import com.jacob.core_lib.instructions.comapare.CompareRegister
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class CompareRegisterParserTest {
    @Test
    internal fun `created correct compare instruction 1`() {
        val instructionString = InstructionString("CMP R1, R2")
        val instruction = CompareRegisterParser(instructionString).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` CompareRegister::class
            this as CompareRegister
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 2.SR
        }
    }

    @Test
    internal fun `created correct compare instruction 2`() {
        val instructionString = InstructionString("CMP R1, R12")
        val instruction = CompareRegisterParser(instructionString).parse()

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction.apply {
            this `should be instance of` CompareRegister::class
            this as CompareRegister
            sourceRegister1 `should be equal to` 1.SR
            sourceRegister2 `should be equal to` 12.SR
        }
    }
}
