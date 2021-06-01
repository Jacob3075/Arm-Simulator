package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.regex.InstructionRegex
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.TYPES
import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.conditional.ConditionalParser
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser

data class InstructionString(private var instructionString: String) {

    // ADD R1, R2, R3
    // ADD R1, R2, #3
    // ADDEQ R1, R2, #3
    // ADD R1, R2, #3, LSL #4
    // ADDEQ R1, R2, #3, LSL #4
    val mnemonic: String
    val conditional: Conditionals
    val mainInstruction: String
    val operands: List<String>
    val shiftOperation: ShiftOperation

    init {
        val matchResult = InstructionRegex.Shifts.TYPES.find(instructionString)
        mainInstruction = processMainInstruction(matchResult)
        shiftOperation = processShiftOperation(matchResult)
        mnemonic = processMnemonic()
        conditional = processConditional()
        operands = processOperands()
    }

    private fun processMnemonic() = instructionString.split(" ").first().trim()

    private fun processOperands() = mainInstruction.replaceFirst(mnemonic, "")
        .replace("[", "")
        .replace("]", "")
        .replace("!", "")
        .split(",")
        .map(String::trim)

    private fun processMainInstruction(matchResult: MatchResult?): String {
        val temp = matchResult?.let {
            instructionString.substring(0 until matchResult.range.first)
        } ?: instructionString

        return temp.trim()
            .removeSuffix(",")
            .trim()
    }

    private fun processShiftOperation(operationMatch: MatchResult?): ShiftOperation {
        operationMatch ?: return ShiftOperation.None

        val operationSubString = instructionString.substring(startIndex = operationMatch.range.first).trim()
        return ShiftOperationParser.from(operationSubString).parse()
    }

    private fun processConditional(): Conditionals {
        val conditionalString = if (mnemonic.contains(TYPES)) mnemonic.takeLast(2) else ""
        return ConditionalParser.parseConditional(conditionalString)
    }
}
