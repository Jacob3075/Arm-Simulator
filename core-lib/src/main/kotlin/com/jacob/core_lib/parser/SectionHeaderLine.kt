package com.jacob.core_lib.parser

enum class SectionType {
    DATA, TEXT
}

class SectionHeaderLine(private val sectionType: SectionType) : Line {
    override fun parse() = SectionHeader(sectionType)
}

data class SectionHeader(private val sectionType: SectionType)
