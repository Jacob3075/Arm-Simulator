package com.jacob.core_lib.parser.data

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.core.Variable

data class ParsedData(val variableName: String, val variableValue: Int)

fun List<ParsedData>.toVariables() = this.mapIndexed { index, parsedData ->
    Variable(parsedData.variableName, MemoryAddress(index))
}
