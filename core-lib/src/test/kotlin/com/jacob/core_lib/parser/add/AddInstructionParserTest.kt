package com.jacob.core_lib.parser.add

import com.jacob.core_lib.parser.InstructionParser
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class AddInstructionParserTest {
    @Test
    internal fun `returns add register instruction parser`() {
        val instructionString = "ADD R1, R2, R3"

        val addInstructionParser = AddInstructionParser.from(instructionString)

        addInstructionParser.shouldNotBeNull()
        addInstructionParser `should be instance of` InstructionParser::class
        addInstructionParser `should be instance of` AddRegisterInstructionParser::class
    }

    @Test
    internal fun `returns add immediate instruction parser`() {
        val instructionString = "ADD R1, R2, #3"

        val addInstructionParser = AddInstructionParser.from(instructionString)

        addInstructionParser.shouldNotBeNull()
        addInstructionParser `should be instance of` InstructionParser::class
        addInstructionParser `should be instance of` AddImmediateInstructionParser::class
    }
}
