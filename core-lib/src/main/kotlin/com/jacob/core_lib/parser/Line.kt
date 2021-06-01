package com.jacob.core_lib.parser

import com.jacob.core_lib.common.regex.General
import com.jacob.core_lib.parser.data.DataLine
import com.jacob.core_lib.parser.instructions.InstructionLine
import com.jacob.core_lib.parser.instructions.InstructionString

interface Line {
    companion object {
        fun from(line: String) = with(line) {
            when {
                matches(General.Headers.DATA) -> SectionHeaderLine(SectionType.DATA)
                matches(General.Headers.TEXT) -> SectionHeaderLine(SectionType.TEXT)
                matches(General.Headers.END) -> SectionHeaderLine(SectionType.END)
                matches(General.WORD) -> DataLine(line)
                else -> InstructionLine(InstructionString(line))
            }
        }
    }

    fun parse(): Any
}
