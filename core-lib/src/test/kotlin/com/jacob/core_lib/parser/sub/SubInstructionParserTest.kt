package com.jacob.core_lib.parser.sub

import com.jacob.core_lib.parser.InstructionParser
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class SubInstructionParserTest {
    @Test
    internal fun `returns sub register instruction parser`() {
        val instructionString = "SUB R1, R2, R3"

        val subInstructionParser = SubInstructionParser.from(instructionString)

        subInstructionParser.shouldNotBeNull()
        subInstructionParser `should be instance of` InstructionParser::class
        subInstructionParser `should be instance of` SubRegisterInstructionParser::class
    }

    @Test
    internal fun `returns sub immediate instruction parser`() {
        val instructionString = "SUB R1, R2, #3"

        val subInstructionParser = SubInstructionParser.from(instructionString)

        subInstructionParser.shouldNotBeNull()
        subInstructionParser `should be instance of` InstructionParser::class
        subInstructionParser `should be instance of` SubImmediateInstructionParser::class
    }
}
