package com.jacob.core_lib.core

import com.jacob.core_lib.common.addresses.MemoryAddress

data class ParsedVariable(val variableName: String, val variableValue: Int)

fun List<ParsedVariable>.toVariables() = this.mapIndexed { index, parsedData ->
    Variable(parsedData.variableName, MemoryAddress(index))
}
