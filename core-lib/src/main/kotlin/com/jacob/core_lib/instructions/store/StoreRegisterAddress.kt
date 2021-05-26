package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

data class StoreRegisterAddress(
    internal val sourceRegister: SourceRegister,
    internal val destinationRegister: DestinationRegister,
) : Store {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromRegister = executionEnvironment.registerArray
            .getRegisterAt(sourceRegister)
            .getRegisterValue()

        val destinationMemoryAddress = executionEnvironment.registerArray
            .getRegisterAt(destinationRegister)
            .getRegisterValue()
            .value
            .let(::MemoryAddress)

        executionEnvironment.memoryArray.setWordAt(destinationMemoryAddress, wordFromRegister)
    }

}
