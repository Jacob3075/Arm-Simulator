package com.jacob.core_lib.parser

import com.jacob.core_lib.core.Variable
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.instructions.InstructionLine
import java.io.File
import kotlin.reflect.KClass

typealias Instructions = List<Instruction>
typealias Variables = List<Variable>
typealias Lines = List<Line>

object Parser {
    fun parseDataFromFile(file: File): Pair<Instructions, Variables> {
        val linesByType: Map<KClass<out Line>, Lines> = file.readLines()
            .asSequence()
            .map(String::trim)
            .map(String::uppercase)
            .map(Line::from)
            .groupBy { it::class }

        val instructionLines = linesByType[InstructionLine::class]
            ?.map(Line::parse)
            ?.map { it as Instruction }
            ?: listOf()

        val variableLines = linesByType[VariableLine::class]
            ?.map(Line::parse)
            ?.map { it as Variable }
            ?: listOf()

        return Pair(instructionLines, variableLines)
    }
}
