package com.jacob.core_lib.parser

import com.jacob.core_lib.common.regex.General
import com.jacob.core_lib.parser.data.DataLine
import com.jacob.core_lib.parser.instructions.InstructionLine

interface Line {
    companion object {
        fun from(line: String) = when {
            line.matches(General.Headers.DATA) -> SectionHeaderLine(SectionType.DATA)
            line.matches(General.Headers.TEXT) -> SectionHeaderLine(SectionType.TEXT)
            line.matches(General.Headers.END) -> SectionHeaderLine(SectionType.END)
            line.matches(General.WORD) -> DataLine(line)
            else -> InstructionLine(line)
        }
    }

    fun parse(): Any
}
