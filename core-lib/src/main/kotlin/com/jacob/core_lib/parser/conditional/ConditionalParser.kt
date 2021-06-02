package com.jacob.core_lib.parser.conditional

import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.ALWAYS
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.CARRY_CLEAR
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.CARRY_SET
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.EQUAL
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.NOTEQUAL
import com.jacob.core_lib.instructions.conditionals.Conditionals

object ConditionalParser {
    fun parseConditional(conditionalString: String) = with(conditionalString) {
        when {
            isEmpty() || matches(ALWAYS) -> Conditionals.AL
            matches(EQUAL) -> Conditionals.EQ
            matches(NOTEQUAL) -> Conditionals.NE
            matches(CARRY_SET) -> Conditionals.CS
            matches(CARRY_CLEAR) -> Conditionals.CC
            else -> throw IllegalArgumentException("Cannot parse conditional code: $conditionalString")
        }
    }
}
