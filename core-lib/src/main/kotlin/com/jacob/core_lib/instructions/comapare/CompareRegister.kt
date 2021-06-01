package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class CompareRegister internal constructor(
    internal val sourceRegister1: SourceRegister,
    internal val sourceRegister2: SourceRegister
) : Compare {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray) = executionEnvironment

        val registerValue1 = registerArray.getRegisterAt(sourceRegister1).getRegisterValue()
        val registerValue2 = registerArray.getRegisterAt(sourceRegister2).getRegisterValue()

        registerArray.statusRegister.updateCPSR(
            value1 = registerValue1.value,
            value2 = registerValue2.value,
            operation = Int::minus
        )
    }
}
