package com.jacob.core_lib.registers

class ProgramCounter {
    var nextInstructionAddress: Int = 0
        private set

    fun nextInstruction() {
        nextInstructionAddress++
    }

    fun updateProgramCounter(nextInstructionAddress: Int) {
        if (nextInstructionAddress < 0)
            throw IllegalArgumentException("Label Index Cannot Be Negative, $nextInstructionAddress")

        this.nextInstructionAddress = nextInstructionAddress
    }

}
