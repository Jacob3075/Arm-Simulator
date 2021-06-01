package com.jacob.core_lib.instructions.load

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.core.ExecutionEnvironment

data class LoadVariableAddress internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val variableName: String
) : Load {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val variable = executionEnvironment.variables.find { it.name == variableName }

        require(variable != null)

        executionEnvironment.registerArray.setValueAtRegister(
            destinationRegister,
            variable.address.memoryAddress.W
        )
    }
}
