package com.jacob.core_lib.parser

import com.jacob.core_lib.parser.data.DataLine
import com.jacob.core_lib.parser.instructions.InstructionLine

interface Line {
    companion object {
        fun from(line: String) = when {
            line.contains(".DATA") -> SectionHeaderLine(SectionType.DATA)
            line.contains(".TEXT") -> SectionHeaderLine(SectionType.TEXT)
            line.contains(".END") -> SectionHeaderLine(SectionType.END)
            line.contains(".WORD") -> DataLine(line)
            else -> InstructionLine(line)
        }
    }

    fun parse(): Any
}
