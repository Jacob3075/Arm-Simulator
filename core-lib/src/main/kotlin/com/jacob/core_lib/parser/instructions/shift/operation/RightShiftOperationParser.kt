package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.instructions.shift.RightShift

class RightShiftOperationParser(private val operationString: String) : ShiftOperationParser {
    //        LSR #3
    override fun parse() = operationString.removePrefix("LSR")
        .trim()
        .removePrefix("#")
        .toInt()
        .let(::RightShift)
}
