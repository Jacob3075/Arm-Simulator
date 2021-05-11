package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.instructions.Instruction

interface InstructionParser {

    operator fun invoke(): Instruction
}
