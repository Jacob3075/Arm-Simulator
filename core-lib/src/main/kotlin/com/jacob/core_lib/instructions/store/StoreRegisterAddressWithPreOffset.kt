package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

class StoreRegisterAddressWithPreOffset(
    internal val sourceRegister: SourceRegister,
    internal val destinationRegister: DestinationRegister,
    internal val offset: ImmediateValue
) : Store {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        TODO("Not yet implemented")
    }
}
