package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.word.ImmediateValue

data class MoveImmediate internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val immediateValue:
    ImmediateValue,
) : Move {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, immediateValue)
    }
}
