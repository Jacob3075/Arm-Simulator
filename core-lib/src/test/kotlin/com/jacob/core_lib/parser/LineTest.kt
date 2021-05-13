package com.jacob.core_lib.parser

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test

internal class LineTest {
    @Test
    internal fun `can parse data section header line`() {
        val lineString = ".DATA"
        val line = Line.from(lineString)

        line `should be instance of` SectionHeaderLine::class

        line as SectionHeaderLine
        val sectionHeader = line.parse()

        sectionHeader `should be equal to` SectionHeader(SectionType.DATA)
    }

    @Test
    internal fun `can parse end header line`() {
        val lineString = ".END"
        val line = Line.from(lineString)

        line `should be instance of` SectionHeaderLine::class

        line as SectionHeaderLine
        val sectionHeader = line.parse()

        sectionHeader `should be equal to` SectionHeader(SectionType.END)
    }

    @Test
    internal fun `can parse text section header line`() {
        val lineString = ".TEXT"
        val line = Line.from(lineString)

        line `should be instance of` SectionHeaderLine::class

        line as SectionHeaderLine
        val sectionHeader = line.parse()

        sectionHeader `should be equal to` SectionHeader(SectionType.TEXT)
    }
}
