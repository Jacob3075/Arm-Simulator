package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.instructions.shift.RightRotateShift

class RightRotateShiftOperationParser(private val operationString: String) :
    ShiftOperationParser {
    override fun parse() = operationString.removePrefix("ROR")
        .trim()
        .removePrefix("#")
        .toInt()
        .let(::RightRotateShift)
}
