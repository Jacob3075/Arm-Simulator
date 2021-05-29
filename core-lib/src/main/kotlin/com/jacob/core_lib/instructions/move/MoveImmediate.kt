package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.toInt
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.shift.ShiftOperation
import com.jacob.core_lib.word.ImmediateValue

data class MoveImmediate internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val immediateValue: ImmediateValue,
    internal val shiftOperation: ShiftOperation,
) : Move {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val shiftedValue = shiftOperation.shift(
            immediateValue.value,
            executionEnvironment.registerArray.statusRegister.carry.toInt()
        ).I
        executionEnvironment.registerArray.setValueAtRegister(destinationRegister, shiftedValue)
    }
}
