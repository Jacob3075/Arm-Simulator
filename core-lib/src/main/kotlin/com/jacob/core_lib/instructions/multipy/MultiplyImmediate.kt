package com.jacob.core_lib.instructions.multipy

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.word.ImmediateValue

data class MultiplyImmediate internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val immediateValue: ImmediateValue
) : Multiply {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray: RegisterArray) = executionEnvironment

        val wordFromRegister = registerArray.getRegisterAt(sourceRegister).getRegisterValue()
        val result = wordFromRegister * immediateValue

        registerArray.setValueAtRegister(destinationRegister, result)
    }
}
