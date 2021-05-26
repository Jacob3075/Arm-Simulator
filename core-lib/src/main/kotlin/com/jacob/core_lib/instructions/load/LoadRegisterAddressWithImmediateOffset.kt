package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class LoadRegisterAddressWithImmediateOffset(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val offset: Int,
) : Load {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray, memoryArray) = executionEnvironment

        val sourceAddress = registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()

        val resultAddress = sourceAddress.value + offset

        val wordFromMemory = memoryArray.getWordAt(resultAddress.MA)

        registerArray.setValueAtRegister(destinationRegister.registerAddress, wordFromMemory)
    }
}
