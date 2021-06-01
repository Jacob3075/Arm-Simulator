package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.core.RegisterArray

class MultiplyRegister internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister1: SourceRegister,
    internal val sourceRegister2: SourceRegister
) : Multiply {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray: RegisterArray) = executionEnvironment

        val registerValue1 = registerArray.getRegisterAt(sourceRegister1).getRegisterValue()
        val registerValue2 = registerArray.getRegisterAt(sourceRegister2).getRegisterValue()

        val result = registerValue1 * registerValue2

        registerArray.setValueAtRegister(destinationRegister, result)
    }

}
