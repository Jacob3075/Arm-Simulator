package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

data class StoreVariableAddress internal constructor(
    internal val sourceRegister: SourceRegister,
    internal val variableName: String
) : Store {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromRegister = executionEnvironment.registerArray
            .getRegisterAt(sourceRegister)
            .getRegisterValue()

        val variable = executionEnvironment.variables.find { it.name == variableName }

        require(variable != null)

        executionEnvironment.memoryArray.setWordAt(variable.address, wordFromRegister)
    }
}
