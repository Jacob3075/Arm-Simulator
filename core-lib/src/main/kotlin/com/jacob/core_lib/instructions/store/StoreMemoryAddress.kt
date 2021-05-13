package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class StoreMemoryAddress(
    private val destinationAddress: MemoryAddress,
    private val sourceRegister: SourceRegister,
) : Store {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromRegister =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        executionEnvironment.memoryArray.setWordAt(destinationAddress, wordFromRegister)
    }
}
