package com.jacob.core_lib.parser

import arrow.core.ValidatedNel
import arrow.core.valid
import com.jacob.core_lib.common.Errors

enum class SectionType { DATA, TEXT, END; }

class SectionHeaderLine(private val sectionType: SectionType) : Line {
    override fun parse(): ValidatedNel<Errors, SectionHeader> = SectionHeader(sectionType).valid()
}

data class SectionHeader(private val sectionType: SectionType)
