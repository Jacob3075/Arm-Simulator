package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.instructions.conditionals.Conditionals
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.parser.conditional.ConditionalParser
import com.jacob.core_lib.parser.instructions.shift.operation.ShiftOperationParser
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.TYPES as ConditionalTYPES
import com.jacob.core_lib.common.regex.InstructionRegex.Shifts.TYPES as ShiftTYPES

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
        var tempString = instructionString

        val shiftOperationString = "$ShiftTYPES #(0X)?\\d+".toRegex().find(tempString)?.value ?: ""

        tempString = tempString.replace(shiftOperationString, "").trim().removeSuffix(",").trim()

        val tempList = tempString.replace("[", "")
            .replace("]", "")
            .replace("!", "")
            .split(",", " ")
            .filter(String::isNotEmpty)
            .filter(String::isNotBlank)
            .map(String::trim)

        val name = tempList.first()

        val conditionalString = "$ConditionalTYPES".toRegex().find(name)?.value ?: ""
        tempString = tempString.replace(conditionalString, "")

        operands = tempList.drop(1)
        conditional = ConditionalParser.parseConditional(conditionalString)
        shiftOperation = ShiftOperationParser.from(shiftOperationString)
        mnemonic = name.replace(conditionalString, "")
        mainInstruction = tempString
    }
}
