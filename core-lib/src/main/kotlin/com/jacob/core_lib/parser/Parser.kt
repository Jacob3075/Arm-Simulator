package com.jacob.core_lib.parser

import arrow.core.*
import com.jacob.core_lib.common.Errors
import com.jacob.core_lib.core.ParsedVariable
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.data.DataLine
import com.jacob.core_lib.parser.instructions.InstructionLine
import java.io.File
import kotlin.collections.flatten
import kotlin.reflect.KClass

typealias Instructions = List<Instruction>
typealias Variables = List<ParsedVariable>
typealias ParsedData = Pair<Instructions, Variables>
private typealias Lines = List<Line>
private typealias LineTypes = KClass<out Line>

object Parser {
    fun parseDataFromFile(file: File): Either<Nel<Errors>, ParsedData> {
        val linesByType: Map<LineTypes, Lines> = file.readLines()
            .asSequence()
            .filter(String::isNotEmpty)
            .filter(String::isNotBlank)
            .map(String::trim)
            .map(String::uppercase)
            .map(Line::from)
            .groupBy { it::class }

        if (!(linesByType[SectionHeaderLine::class] ?: emptyList())
                .map { it as SectionHeaderLine }
                .traverseValidated(SectionHeaderLine::parse)
                .toList()
                .flatten()
                .contains(SectionHeader(SectionType.TEXT))
        ) return Errors.HeaderNotPresent().invalidNel().toEither()

        val instructionsOrErrors: Either<Nel<Errors.InvalidInstruction>, Instructions> =
            (linesByType[InstructionLine::class] ?: emptyList())
                .map { it as InstructionLine }
                .traverseValidated(InstructionLine::parse)
                .toEither()

        val variablesOrErrors: Either<Nel<Errors.InvalidVariableDefinition>, Variables> =
            (linesByType[DataLine::class] ?: emptyList())
                .map { it as DataLine }
                .traverseValidated(DataLine::parse)
                .toEither()

        return instructionsOrErrors.zip(variablesOrErrors) { instructions, variables ->
            Pair(instructions, variables)
        }
    }

    fun parseProgramFromFile(file: File): Either<Nel<Errors>, Program> =
        parseDataFromFile(file).map { Program(it.first, it.second) }
}
