package com.jacob.core_lib.parser

import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.InstructionMnemonic.*

class InstructionString(val instruction: String) {

    private val mnemonic: InstructionMnemonic = valueOf(instruction.split(" ").first().uppercase())

    fun parse(): Instruction {
        return when (mnemonic) {
            ADD -> TODO()
            SUB -> TODO()
            MOV -> TODO()
            LDR -> TODO()
            STR -> TODO()
            B -> TODO()
            LABEL -> TODO()
        }
    }
}
