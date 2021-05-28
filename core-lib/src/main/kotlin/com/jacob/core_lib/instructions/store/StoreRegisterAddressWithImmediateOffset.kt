package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.MA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

data class StoreRegisterAddressWithImmediateOffset(
    internal val sourceRegister: SourceRegister,
    internal val destinationRegister: DestinationRegister,
    internal val offset: ImmediateValue
) : Store {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray, memoryArray) = executionEnvironment

        val valueToSave = registerArray.getRegisterAt(sourceRegister).getRegisterValue()

        val addressFromRegister = registerArray.getRegisterAt(destinationRegister).getRegisterValue()
        val resultAddress = addressFromRegister + offset

        memoryArray.setWordAt(resultAddress.value.MA, valueToSave)
    }
}
