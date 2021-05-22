package com.jacob.core_lib.instructions.comapare

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class CompareRegister(
    private val sourceRegister1: SourceRegister,
    private val sourceRegister2: SourceRegister
) : Compare {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val (registerArray) = executionEnvironment

        val registerValue1 = registerArray.getRegisterAt(sourceRegister1.registerAddress).getRegisterValue()
        val registerValue2 = registerArray.getRegisterAt(sourceRegister2.registerAddress).getRegisterValue()

        registerArray.statusRegister.updateCPSR(
            value1 = registerValue1.value,
            value2 = registerValue2.value,
            operation = Int::minus
        )
    }
}
