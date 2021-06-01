package com.jacob.core_lib.parser.instructions.shift.operation

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class RightRotateShiftParserTest {
    @Test
    internal fun `creates correct shift operation 1`() {
        val operationString = "ROR #4"
        val rightRotateShift = RightRotateShiftParser(operationString).parse()

        rightRotateShift.shiftAmount `should be equal to` 4
    }

    @Test
    internal fun `creates correct shift operation 2`() {
        val operationString = "ROR #14"
        val rightRotateShift = RightRotateShiftParser(operationString).parse()

        rightRotateShift.shiftAmount `should be equal to` 14
    }
}
