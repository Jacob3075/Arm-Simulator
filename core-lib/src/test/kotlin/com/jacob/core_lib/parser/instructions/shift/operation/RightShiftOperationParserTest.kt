package com.jacob.core_lib.parser.instructions.shift.operation

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class RightShiftOperationParserTest {
    @Test
    internal fun `creates correct shift operation 1`() {
        val operationString = "LSR #4"
        val rightShift = RightShiftParser(operationString).parse()

        rightShift.shiftAmount `should be equal to` 4
    }

    @Test
    internal fun `creates correct shift operation 2`() {
        val operationString = "LSR #14"
        val rightShift = RightShiftParser(operationString).parse()

        rightShift.shiftAmount `should be equal to` 14
    }
}
