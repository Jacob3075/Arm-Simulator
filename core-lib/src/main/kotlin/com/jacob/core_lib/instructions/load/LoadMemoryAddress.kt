package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.core.ExecutionEnvironment

data class LoadMemoryAddress internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceAddress: MemoryAddress
) : Load {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromMemory = executionEnvironment.memoryArray.getWordAt(sourceAddress)
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister, wordFromMemory)
    }
}
