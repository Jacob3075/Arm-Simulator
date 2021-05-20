package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.instructions.Instruction

interface InstructionParser {

    //    TODO: CHANGE FUNCTION NAME
    operator fun invoke(): Instruction
}
