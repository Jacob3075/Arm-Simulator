package com.jacob.core_lib.parser

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class VariableLineTest {
    @Test
    internal fun `creates correct variable when name has single letter`() {
        val line = "A: .WORD 10"

        val parse = VariableLine(line).parse()

        parse.name `should be equal to` "A"
        parse.address `should be equal to` 10
    }

    @Test
    internal fun `creates correct variable when given negative value`() {
        val line = "A: .WORD -10"

        val parse = VariableLine(line).parse()

        parse.name `should be equal to` "A"
        parse.address `should be equal to` -10
    }

    @Test
    internal fun `creates correct variable when name has multiple letters`() {
        val line = "ABC: .WORD 10"

        val parse = VariableLine(line).parse()

        parse.name `should be equal to` "ABC"
        parse.address `should be equal to` 10
    }

    @Test
    internal fun `creates correct variable when name has multiple letters and negative value`() {
        val line = "ABC: .WORD -10"

        val parse = VariableLine(line).parse()

        parse.name `should be equal to` "ABC"
        parse.address `should be equal to` -10
    }

    @Test
    internal fun `creates correct variable when name contains word in it`() {
        val line = "ABWORDCD: .WORD 10"

        val parse = VariableLine(line).parse()

        parse.name `should be equal to` "ABWORDCD"
        parse.address `should be equal to` 10
    }
}
