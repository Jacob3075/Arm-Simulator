package com.jacob.core_lib.parser

import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.common.regex.InstructionRegex.Conditionals
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser

class InstructionString(private var instructionString: String) {
    // ADD R1, R2, R3
    // ADD R1, R2, #3
    // ADD R1, R2, #3, LSL #4
    // ADDEQ R1, R2, #3
    var mnemonic: String? = null
    var conditionalCode: String? = null
    var operands: List<String>? = null
    var shiftOperation: ShiftOperation? = null

    init {
        InstructionRegex.Shifts.TYPES.find(instructionString)?.let(::getShiftOperation)
        mnemonic = instructionString.split(" ").first().trim()
        getConditionalCode()
        operands = instructionString.replace(mnemonic!!, "")
            .split(",")
            .map(String::trim)
    }

    private fun getShiftOperation(operationMatch: MatchResult) {
        instructionString = instructionString.substring(0 until operationMatch.range.first)
            .trim()
            .removeSuffix(",")
        val operationSubString = instructionString.substring(startIndex = operationMatch.range.first).trim()

        shiftOperation = ShiftOperationParser.from(operationSubString).parse()
    }

    private fun getConditionalCode() {
        if (mnemonic!!.contains(Conditionals.TYPES)) conditionalCode = mnemonic?.takeLast(2)
    }
}
