package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.instructions.shift.RightRotateExtendedShift

class RightRotateExtendedShiftOperationParser(private val operationString: String) :
    ShiftOperationParser {
    override fun parse() = operationString.removePrefix("RRX")
        .trim()
        .removePrefix("#")
        .toInt()
        .let(::RightRotateExtendedShift)
}
