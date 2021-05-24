package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.word.ImmediateValue

data class SubImmediate internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val immediateValue: ImmediateValue,
    internal val shiftOperation: ShiftOperation,
) : Sub {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val registerValue =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()

        val shiftedValue = shiftOperation.shift(immediateValue.value).W
        val result = registerValue - shiftedValue

        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, result)
    }
}
