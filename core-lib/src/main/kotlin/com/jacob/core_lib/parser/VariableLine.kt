package com.jacob.core_lib.parser

import com.jacob.core_lib.core.Variable

class VariableLine(private val line: String) : Line {
    //    A: .WORD 64
    override fun parse(): Variable {
        val tokens = line.replace(".WORD", "")
            .split(":")
            .map(String::trim)

        require(tokens.size == 2)

        val variableName = tokens.first()

        val variableValue = tokens.last()
            .toInt()

        return Variable(variableName, variableValue)
    }
}
