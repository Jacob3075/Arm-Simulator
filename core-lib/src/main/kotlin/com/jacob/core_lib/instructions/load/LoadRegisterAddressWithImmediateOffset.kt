package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

class LoadRegisterAddressWithImmediateOffset(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val offset: ImmediateValue,
) : Load {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray, memoryArray) = executionEnvironment

        val sourceAddress = registerArray.getRegisterAt(sourceRegister).getRegisterValue()

        val resultAddress = sourceAddress + offset

        val wordFromMemory = memoryArray.getWordAt(resultAddress.value.MA)

        registerArray.setValueAtRegister(destinationRegister, wordFromMemory)
    }
}
