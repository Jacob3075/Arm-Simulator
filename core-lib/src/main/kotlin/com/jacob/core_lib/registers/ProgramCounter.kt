package com.jacob.core_lib.registers

import com.jacob.core_lib.word.Word

class ProgramCounter : Register {
    var nextInstructionAddress: Int = 0

    fun nextInstruction() {
        nextInstructionAddress++
    }

    fun updateProgramCounter(nextInstructionAddress: Int) {
        if (nextInstructionAddress < 0)
            throw IllegalArgumentException("Label Index Cannot Be Negative, $nextInstructionAddress")

        this.nextInstructionAddress = nextInstructionAddress
    }

    override fun getRegisterValue(): Word = Word(nextInstructionAddress)
}
