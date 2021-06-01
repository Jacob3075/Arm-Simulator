package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.instructions.shift.LeftShift

class LeftShiftParser(private val operationString: String) : ShiftOperationParser {
    //        LSL #3
    override fun parse() = operationString.removePrefix("LSL")
        .trim()
        .removePrefix("#")
        .toInt()
        .let(::LeftShift)
}
