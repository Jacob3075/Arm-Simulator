package com.jacob.core_lib.instructions.store

import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment

class StoreVariableAddress(
    internal val sourceRegister: SourceRegister,
    internal val variableName: String
) : Store {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val wordFromRegister = executionEnvironment.registerArray
            .getRegisterAt(sourceRegister.registerAddress)
            .getRegisterValue()

        val variable = executionEnvironment.variables.find { it.name == variableName }
            ?: throw IllegalArgumentException("Cannot find variable with name $variableName")

        executionEnvironment.memoryArray.setWordAt(variable.address, wordFromRegister)
    }
}