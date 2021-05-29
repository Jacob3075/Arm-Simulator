package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toInt
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.shift.ShiftOperation

data class SubRegister internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister1: SourceRegister,
    internal val sourceRegister2: SourceRegister,
    val shiftOperation: ShiftOperation,
) : Sub {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val registerValue1 =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister1).getRegisterValue()
        val registerValue2 =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister2).getRegisterValue()

        val shiftedValue = shiftOperation.shift(
            registerValue2.value,
            executionEnvironment.registerArray.statusRegister.carry.toInt()
        ).W
        val result = registerValue1 - shiftedValue

        executionEnvironment.registerArray.setValueAtRegister(destinationRegister, result)
    }
}
