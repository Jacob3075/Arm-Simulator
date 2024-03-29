package com.jacob.core_lib.parser.instructions.label

import arrow.core.getOrElse
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.parser.instructions.InstructionString
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LabelParserTest {
    @Test
    internal fun `creates correct label when given single letter label name`() {
        val instructionString = InstructionString("A:")
        val label = LabelParser.from(instructionString).getOrElse { Label("") }

        label.labelName `should be equal to` "A"
    }

    @Test
    internal fun `creates correct label when given multiple letter label name`() {
        val instructionString = InstructionString("ABC-A343_V2:")
        val label = LabelParser.from(instructionString).getOrElse { Label("") }

        label.labelName `should be equal to` "ABC-A343_V2"
    }
}
