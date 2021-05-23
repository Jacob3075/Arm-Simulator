package com.jacob.core_lib.parser.instructions.compare

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.immediateFromDec
import com.jacob.core_lib.common.immediateFromHex
import com.jacob.core_lib.instructions.comapare.CompareImmediate
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class CompareImmediateInstructionParserTest {
    @Test
    internal fun `created correct compare instruction with positive decimal values`() {
        val instructionString = "CMP R1, #6"
        val instruction = CompareImmediateInstructionParser(instructionString, String::immediateFromDec).parse()

        instruction `should be instance of` CompareImmediate::class

        instruction.sourceRegister `should be equal to` SourceRegister(1.RA)
        instruction.immediateValue `should be equal to` 6.I
    }

    @Test
    internal fun `created correct compare instruction negative decimal values`() {
        val instructionString = "CMP R1, #-7"
        val instruction = CompareImmediateInstructionParser(instructionString, String::immediateFromDec).parse()

        instruction `should be instance of` CompareImmediate::class

        instruction.sourceRegister `should be equal to` SourceRegister(1.RA)
        instruction.immediateValue `should be equal to` (-7).I
    }

    @Test
    internal fun `created correct compare instruction with hex values`() {
        val instructionString = "CMP R1, #0XCE1CA2"
        val instruction = CompareImmediateInstructionParser(instructionString, String::immediateFromHex).parse()

        instruction `should be instance of` CompareImmediate::class

        instruction.sourceRegister `should be equal to` SourceRegister(1.RA)
        instruction.immediateValue `should be equal to` (13507746).I
    }

}
