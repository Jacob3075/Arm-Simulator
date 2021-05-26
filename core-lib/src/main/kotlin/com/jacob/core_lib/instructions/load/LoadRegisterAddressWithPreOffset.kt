package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

class LoadRegisterAddressWithPreOffset(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val offset: ImmediateValue
) : Load {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        TODO("Not yet implemented")
    }
}
