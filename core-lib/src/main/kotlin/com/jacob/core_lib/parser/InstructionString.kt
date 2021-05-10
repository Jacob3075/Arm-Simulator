package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.InstructionMnemonic.*
import com.jacob.core_lib.parser.add.AddInstructionParser
import com.jacob.core_lib.parser.move.MoveInstructionParser
import com.jacob.core_lib.parser.sub.SubInstructionParser

class InstructionString(val instruction: String) {

    private val mnemonic: InstructionMnemonic = valueOf(instruction.split(" ").first().uppercase())

    fun parse(): Instruction {
        return when (mnemonic) {
            ADD   -> AddInstructionParser.from(instruction)
            SUB   -> SubInstructionParser.from(instruction)
            MOV   -> MoveInstructionParser.from(instruction)
            LDR   -> TODO()
            STR   -> TODO()
            B     -> TODO()
            LABEL -> TODO()
        }
    }
}
