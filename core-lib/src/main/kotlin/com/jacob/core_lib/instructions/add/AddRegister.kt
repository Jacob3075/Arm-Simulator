package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class AddRegister internal constructor(
    private val destinationRegister: DestinationRegister,
    private val sourceRegister1: SourceRegister,
    private val sourceRegister2: SourceRegister
) : Add {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val registerValue1 =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister1.registerAddress).getRegisterValue()
        val registerValue2 =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister2.registerAddress).getRegisterValue()
        val resultWord = registerValue1 + registerValue2
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, resultWord)
    }
}
