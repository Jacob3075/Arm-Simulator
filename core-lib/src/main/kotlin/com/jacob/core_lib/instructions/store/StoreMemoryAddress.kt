package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

data class StoreMemoryAddress internal constructor(
    private val destinationAddress: MemoryAddress,
    private val sourceRegister: SourceRegister,
) : Store {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromRegister =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister).getRegisterValue()
        executionEnvironment.memoryArray.setWordAt(destinationAddress, wordFromRegister)
    }
}
