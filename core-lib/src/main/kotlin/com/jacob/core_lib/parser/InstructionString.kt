package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.InstructionMnemonic.*
import com.jacob.core_lib.parser.add.AddInstructionParser
import com.jacob.core_lib.parser.sub.SubInstructionParser

class InstructionString(val instruction: String) {

    private val mnemonic: InstructionMnemonic = valueOf(instruction.split(" ").first().uppercase())

    fun parse(): Instruction {
        return when (mnemonic) {
            ADD -> AddInstructionParser.from(instruction).invoke()
            SUB -> SubInstructionParser.from(instruction).invoke()
            MOV -> TODO()
            LDR -> TODO()
            STR -> TODO()
            B -> TODO()
            LABEL -> TODO()
        }
    }
}