package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

data class AddImmediate internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val immediateValue: ImmediateValue,
) : Add {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val registerValue =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        val result = immediateValue + registerValue
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, result)
    }
}
