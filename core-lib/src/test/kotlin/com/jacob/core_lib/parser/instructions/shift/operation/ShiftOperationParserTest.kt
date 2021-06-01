package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.instructions.shift.LeftShift
import com.jacob.core_lib.instructions.shift.RightShift
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class ShiftOperationParserTest {
    @Test
    internal fun `can create left shift operation parser`() {
        val operationString = "LSL #4"
        val operationParser = ShiftOperationParser.from(operationString)

        operationParser `should be instance of` LeftShift::class
    }

    @Test
    internal fun `can create right shift operation parser`() {
        val operationString = "LSR #4"
        val operationParser = ShiftOperationParser.from(operationString)

        operationParser `should be instance of` RightShift::class
    }
}
