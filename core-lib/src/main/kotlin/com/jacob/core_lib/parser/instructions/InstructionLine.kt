package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.Line
import com.jacob.core_lib.parser.instructions.InstructionMnemonic.*
import com.jacob.core_lib.parser.instructions.add.AddInstructionParser
import com.jacob.core_lib.parser.instructions.move.MoveInstructionParser
import com.jacob.core_lib.parser.instructions.sub.SubInstructionParser

class InstructionLine(val instruction: String) : Line {

    private val mnemonic: InstructionMnemonic = valueOf(instruction.split(" ").first().uppercase())

    override fun parse(): Instruction {
        return when (mnemonic) {
            ADD -> AddInstructionParser.from(instruction)
            SUB -> SubInstructionParser.from(instruction)
            MOV -> MoveInstructionParser.from(instruction)
            LDR -> TODO()
            STR -> TODO()
            B -> TODO()
            LABEL -> TODO()
        }
    }
}
