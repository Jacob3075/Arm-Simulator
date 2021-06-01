package com.jacob.core_lib.parser.instructions.shift.operation

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class RightRotateExtendedShiftParserTest {
    @Test
    internal fun `creates correct shift operation 1`() {
        val operationString = "RRX #4"
        val rightRotateExtendedShift = RightRotateExtendedShiftParser(operationString).parse()

        rightRotateExtendedShift.shiftAmount `should be equal to` 4
    }

    @Test
    internal fun `creates correct shift operation 2`() {
        val operationString = "RRX #14"
        val rightRotateExtendedShift = RightRotateExtendedShiftParser(operationString).parse()

        rightRotateExtendedShift.shiftAmount `should be equal to` 14
    }
}
