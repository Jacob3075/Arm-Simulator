package com.jacob.core_lib.parser.instructions.shift.operation

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class LeftShiftParserTest {
    @Test
    internal fun `creates correct shift operation 1`() {
        val operationString = "LSL #4"
        val leftShift = LeftShiftParser(operationString).parse()

        leftShift.shiftAmount `should be equal to` 4
    }

    @Test
    internal fun `creates correct shift operation 2`() {
        val operationString = "LSL #14"
        val leftShift = LeftShiftParser(operationString).parse()

        leftShift.shiftAmount `should be equal to` 14
    }
}
