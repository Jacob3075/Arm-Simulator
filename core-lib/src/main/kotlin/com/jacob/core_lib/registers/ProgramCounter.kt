package com.jacob.core_lib.registers

class ProgramCounter {
    var nextInstructionAddress: Int = 0
        set(value) {
            field = if (value < 0) field else value
        }

    fun nextInstruction() {
        nextInstructionAddress++
    }
}
