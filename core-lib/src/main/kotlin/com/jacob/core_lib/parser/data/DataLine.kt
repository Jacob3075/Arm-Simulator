package com.jacob.core_lib.parser.data

import arrow.core.ValidatedNel
import arrow.core.invalidNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors.InvalidVariableDefinition
import com.jacob.core_lib.core.ParsedVariable
import com.jacob.core_lib.parser.Line

class DataLine(private val line: String) : Line {
    //    A: .WORD 64
    override fun parse(): ValidatedNel<InvalidVariableDefinition, ParsedVariable> {
        val tokens = line.replace(".WORD", "")
            .split(":")
            .map(String::trim)

        if (tokens.size != 2) return InvalidVariableDefinition("Cannot parse variable: $line").invalidNel()

        val variableName = tokens.first()
        val variableValue = tokens.last().toInt()

        return ParsedVariable(variableName, variableValue).valid()
    }
}
