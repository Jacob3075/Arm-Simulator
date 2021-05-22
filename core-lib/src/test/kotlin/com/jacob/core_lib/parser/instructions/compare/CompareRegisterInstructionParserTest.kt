package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.comapare.CompareRegister
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class CompareRegisterInstructionParserTest {
    @Test
    internal fun `created correct compare instruction 1`() {
        val instructionString = "CMP R1, R2"
        val instruction = CompareRegisterInstructionParser(instructionString).invoke()

        instruction `should be instance of` CompareRegister::class

        instruction.sourceRegister1 `should be equal to` SourceRegister(1.RA)
        instruction.sourceRegister2 `should be equal to` SourceRegister(2.RA)
    }

    @Test
    internal fun `created correct compare instruction 2`() {
        val instructionString = "CMP R1, R2"
        val instruction = CompareRegisterInstructionParser(instructionString).invoke()

        instruction `should be instance of` CompareRegister::class

        instruction.sourceRegister1 `should be equal to` SourceRegister(1.RA)
        instruction.sourceRegister2 `should be equal to` SourceRegister(2.RA)
    }
}
