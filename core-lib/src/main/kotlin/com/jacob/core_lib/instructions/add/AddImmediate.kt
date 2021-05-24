package com.jacob.core_lib.instructions.add

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.word.ImmediateValue

data class AddImmediate internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val immediateValue: ImmediateValue,
    internal val shiftOperation: ShiftOperation,
) : Add {

    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val registerValue =
            executionEnvironment.registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()

        val shiftedValue = shiftOperation.shift(immediateValue.value).W

        val result = shiftedValue + registerValue
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister.registerAddress, result)
    }
}
