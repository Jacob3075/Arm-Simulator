package com.jacob.core_lib.parser.conditional

import com.jacob.core_lib.common.regex.InstructionRegex.Conditional
import com.jacob.core_lib.instructions.conditionals.Conditionals

object ConditionalParser {
    fun parseConditional(conditionalString: String): Conditionals {
        return when {
            conditionalString.isEmpty() -> Conditionals.AL
            conditionalString.matches(Conditional.Equal) -> Conditionals.EQ
            conditionalString.matches(Conditional.NotEqual) -> Conditionals.NE
            conditionalString.matches(Conditional.CarrySet) -> Conditionals.CS
            conditionalString.matches(Conditional.CarryClear) -> Conditionals.CC
            else -> throw IllegalArgumentException("Cannot parse conditional code: $conditionalString")
        }
    }
}
