package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

class SubImmediate internal constructor(
    private val destinationRegister: DestinationRegister,
    private val sourceRegister: SourceRegister,
    private val immediateValue: ImmediateValue,
) : Sub {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val registerValue =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        val result = registerValue - immediateValue
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, result)
    }
}
