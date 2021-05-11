package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class Store(private val sourceRegister: SourceRegister, private val destinationAddress: MemoryAddress) :
    Instruction {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromRegister =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        executionEnvironment.memoryArray.setWordAt(destinationAddress, wordFromRegister)
    }
}
