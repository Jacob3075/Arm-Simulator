package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

data class StoreRegisterAddressWithPostOffset(
    internal val sourceRegister: SourceRegister,
    internal val destinationRegister: DestinationRegister,
    internal val offset: ImmediateValue
) : Store {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray, memoryArray) = executionEnvironment

        val destinationAddress = registerArray.getRegisterAt(destinationRegister).getRegisterValue()
        val wordToStore = registerArray.getRegisterAt(sourceRegister).getRegisterValue()
        memoryArray.setWordAt(destinationAddress.value.MA, wordToStore)

        val resultAddress = destinationAddress + offset
        registerArray.setValueAtRegister(destinationRegister, resultAddress)
    }
}
