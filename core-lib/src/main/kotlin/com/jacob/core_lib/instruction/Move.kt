package com.jacob.core_lib.instruction

import com.jacob.core_lib.register.DestinationRegister

class Move(val destinationRegister: DestinationRegister, val immediateValue: ImmediateValue): Instruction {
    override fun execute() {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Move(destinationRegister=$destinationRegister, immediateValue=$immediateValue)"
    }
}
