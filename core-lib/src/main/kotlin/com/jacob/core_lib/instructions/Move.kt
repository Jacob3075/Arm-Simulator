package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.word.ImmediateValue

class Move(private val destinationRegister: DestinationRegister, private val immediateValue: ImmediateValue) :
    Instruction {

    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        registerArray.setValueAtRegister(destinationRegister.registerAddress, immediateValue)
    }

    override fun toString(): String {
        return "Move(destinationRegister=$destinationRegister, immediateValue=$immediateValue)"
    }
}
