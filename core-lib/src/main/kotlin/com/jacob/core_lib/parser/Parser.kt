package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.DataLine
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.parser.instructions.InstructionLine
import java.io.File
import kotlin.reflect.KClass

private typealias Instructions = List<Instruction>
private typealias Variables = List<ParsedData>
private typealias Lines = List<Line>
private typealias LineTypes = KClass<out Line>

object Parser {
    fun parseDataFromFile(file: File): Pair<Instructions, Variables> {
        val linesByType: Map<LineTypes, Lines> = file.readLines()
            .asSequence()
            .filter(String::isNotEmpty)
            .filter(String::isNotBlank)
            .map(String::trim)
            .map(String::uppercase)
            .map(Line::from)
            .groupBy { it::class }

        require(linesByType[SectionHeaderLine::class]
            ?.map { it as SectionHeaderLine }
            ?.map(SectionHeaderLine::parse)
            ?.contains(SectionHeader(SectionType.TEXT))
            ?: false
        )

        val instructionLines = linesByType[InstructionLine::class]
            ?.map { it as InstructionLine }
            ?.map(InstructionLine::parse)
            ?: listOf()

        // TODO
        val dataLines = linesByType[DataLine::class]
            ?.map { it as DataLine }
            ?.map(DataLine::parse)
            ?: listOf()

        return Pair(instructionLines, dataLines)
    }
}
