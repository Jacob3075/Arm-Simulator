package com.jacob.core_lib.parser.conditional

import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.CarryClear
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.CarrySet
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.Equal
import com.jacob.core_lib.common.regex.InstructionRegex.Conditional.NotEqual
import com.jacob.core_lib.instructions.conditionals.Conditionals

object ConditionalParser {
    fun parseConditional(conditionalString: String) = with(conditionalString) {
        when {
            isEmpty() -> Conditionals.AL
            matches(Equal) -> Conditionals.EQ
            matches(NotEqual) -> Conditionals.NE
            matches(CarrySet) -> Conditionals.CS
            matches(CarryClear) -> Conditionals.CC
            else -> throw IllegalArgumentException("Cannot parse conditional code: $conditionalString")
        }
    }
}
