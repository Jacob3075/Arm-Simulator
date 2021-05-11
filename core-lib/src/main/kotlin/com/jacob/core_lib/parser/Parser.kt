package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction
import java.io.File

object Parser {
    fun parseInstructionsFromFile(file: File): List<Instruction> = file.readLines()
        .map(String::trim)
        .map(String::uppercase)
        .map(::InstructionString)
        .map(InstructionString::parse)
}
