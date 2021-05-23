package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.instructions.Instruction

interface InstructionParser {

    fun parse(): Instruction
}
