package com.jacob.core_lib.parser.data

import com.jacob.core_lib.core.ParsedData
import com.jacob.core_lib.parser.Line

class DataLine(private val line: String) : Line {
    //    A: .WORD 64
    override fun parse(): ParsedData {
        val tokens = line.replace(".WORD", "")
            .split(":")
            .map(String::trim)

        require(tokens.size == 2)

        val variableName = tokens.first()

        val variableValue = tokens.last()
            .toInt()

        return ParsedData(variableName, variableValue)
    }
}
