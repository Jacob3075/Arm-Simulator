package com.jacob.core_lib.parser.instructions.compare

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.comapare.CompareRegister
import com.jacob.core_lib.instructions.conditionals.Always
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class CompareInstructionParserTest {
    @Test
    internal fun `creates correct instruction`() {
        val instructionString = InstructionString("CMP R1, R2")
        val instruction = CompareInstructionParser.from(instructionString).getOrElse { null }!!

        instruction `should be instance of` Always::class
        instruction as Always

        instruction.instruction `should be instance of` CompareRegister::class
    }
}
