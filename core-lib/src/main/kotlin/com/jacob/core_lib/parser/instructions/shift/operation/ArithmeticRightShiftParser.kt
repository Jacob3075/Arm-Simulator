package com.jacob.core_lib.parser.instructions.shift.operation

import com.jacob.core_lib.instructions.shift.ArithmeticRightShift

class ArithmeticRightShiftParser(private val operationString: String) :
    ShiftOperationParser {
    override fun parse() = operationString.removePrefix("ASR")
        .trim()
        .removePrefix("#")
        .toInt()
        .let(::ArithmeticRightShift)
}
