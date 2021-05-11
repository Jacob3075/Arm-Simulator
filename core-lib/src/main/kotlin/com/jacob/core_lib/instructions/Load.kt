package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.core.ExecutionEnvironment

class Load(private val destinationRegister: DestinationRegister, private val sourceAddress: MemoryAddress) :
    Instruction {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromMemory = executionEnvironment.memoryArray.getWordAt(sourceAddress)
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, wordFromMemory)
    }
}
