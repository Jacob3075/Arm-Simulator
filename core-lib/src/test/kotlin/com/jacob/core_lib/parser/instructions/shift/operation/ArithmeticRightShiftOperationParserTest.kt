package com.jacob.core_lib.parser.instructions.shift.operation

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class ArithmeticRightShiftOperationParserTest {
    @Test
    internal fun `creates correct shift operation 1`() {
        val operationString = "ASR #4"
        val arithmeticRightShift = ArithmeticRightShiftOperationParser(operationString).parse()

        arithmeticRightShift.shiftAmount `should be equal to` 4
    }

    @Test
    internal fun `creates correct shift operation 2`() {
        val operationString = "ASR #14"
        val arithmeticRightShift = ArithmeticRightShiftOperationParser(operationString).parse()

        arithmeticRightShift.shiftAmount `should be equal to` 14
    }
}
