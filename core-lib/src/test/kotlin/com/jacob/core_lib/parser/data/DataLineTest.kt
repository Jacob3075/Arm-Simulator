package com.jacob.core_lib.parser.data

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class DataLineTest {
    @Test
    internal fun `creates correct variable when name has single letter`() {
        val line = "A: .WORD 10"

        val parse = DataLine(line).parse()

        parse.variableName `should be equal to` "A"
        parse.variableValue `should be equal to` 10
    }

    @Test
    internal fun `creates correct variable when given negative value`() {
        val line = "A: .WORD -10"

        val parse = DataLine(line).parse()

        parse.variableName `should be equal to` "A"
        parse.variableValue `should be equal to` -10
    }

    @Test
    internal fun `creates correct variable when name has multiple letters`() {
        val line = "ABC: .WORD 10"

        val parse = DataLine(line).parse()

        parse.variableName `should be equal to` "ABC"
        parse.variableValue `should be equal to` 10
    }

    @Test
    internal fun `creates correct variable when name has multiple letters and negative value`() {
        val line = "ABC: .WORD -10"

        val parse = DataLine(line).parse()

        parse.variableName `should be equal to` "ABC"
        parse.variableValue `should be equal to` -10
    }

    @Test
    internal fun `creates correct variable when name contains word in it`() {
        val line = "ABWORDCD: .WORD 10"

        val parse = DataLine(line).parse()

        parse.variableName `should be equal to` "ABWORDCD"
        parse.variableValue `should be equal to` 10
    }
}
