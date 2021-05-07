package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction

interface InstructionParser {

    operator fun invoke(): Instruction
}
