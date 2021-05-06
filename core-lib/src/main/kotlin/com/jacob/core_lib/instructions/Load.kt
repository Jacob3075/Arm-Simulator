package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray

class Load(private val destinationRegister: DestinationRegister, private val sourceAddress: MemoryAddress) :
    Instruction {

    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        val wordFromMemory = memoryArray.getWordAt(sourceAddress)
        registerArray.setValueAtRegister(destinationRegister.registerAddress, wordFromMemory)
    }
}
