package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

class CompareImmediate(
    internal val sourceRegister: SourceRegister,
    internal val immediateValue: ImmediateValue
) : Compare {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray) = executionEnvironment

        val wordFromRegister = registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()

        registerArray.statusRegister.updateCPSR(wordFromRegister.value, immediateValue.value, Int::minus)
    }
}
