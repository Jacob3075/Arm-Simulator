package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

data class MoveRegister(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister
) :
    Move {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val sourceRegisterValue =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, sourceRegisterValue)
    }
}
