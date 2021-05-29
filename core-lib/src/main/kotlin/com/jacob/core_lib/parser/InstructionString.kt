package com.jacob.core_lib.parser

import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.common.regex.InstructionRegex.Conditionals
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser

class InstructionString(private var instructionString: String) {

    // ADD R1, R2, R3
    // ADD R1, R2, #3
    // ADDEQ R1, R2, #3
    // ADD R1, R2, #3, LSL #4
    // ADDEQ R1, R2, #3, LSL #4
    var mnemonic: String
    var conditionalCode: String
    var coreInstruction: String
    var operands: List<String>
    var shiftOperation: ShiftOperation

    init {
        val matchResult = InstructionRegex.Shifts.TYPES.find(instructionString)
        coreInstruction = matchResult?.let { getMainInstruction(it) } ?: instructionString
        shiftOperation = matchResult?.let { getShiftOperation(it) } ?: ShiftOperation.None
        mnemonic = instructionString.split(" ").first().trim()
        conditionalCode = getConditional()
        operands = coreInstruction.replace(mnemonic, "")
            .split(",")
            .map(String::trim)
    }

    private fun getMainInstruction(matchResult: MatchResult): String {
        return instructionString.substring(0 until matchResult.range.first)
            .trim()
            .removeSuffix(",")
    }

    private fun getShiftOperation(operationMatch: MatchResult): ShiftOperation {
        val operationSubString = instructionString.substring(startIndex = operationMatch.range.first).trim()

        return ShiftOperationParser.from(operationSubString).parse()
    }

    private fun getConditional(): String {
        return if (mnemonic.contains(Conditionals.TYPES)) mnemonic.takeLast(2) else ""
    }
}
