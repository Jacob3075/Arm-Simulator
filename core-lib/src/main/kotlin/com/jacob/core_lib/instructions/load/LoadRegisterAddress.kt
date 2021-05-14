package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

data class LoadRegisterAddress(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
) : Load {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val memoryAddress = executionEnvironment.registerArray
            .getRegisterAt(sourceRegister.registerAddress)
            .getRegisterValue()
            .value
            .let(::MemoryAddress)

        val wordFromMemory = executionEnvironment.memoryArray.getWordAt(memoryAddress)

        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, wordFromMemory)
    }
}
