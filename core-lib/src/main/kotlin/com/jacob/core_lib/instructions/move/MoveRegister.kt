package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.W
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.common.toInt
import com.jacob.core_lib.core.ExecutionEnvironment
import com.jacob.core_lib.instructions.shift.ShiftOperation

data class MoveRegister internal constructor(
    internal val destinationRegister: DestinationRegister,
    internal val sourceRegister: SourceRegister,
    internal val shiftOperation: ShiftOperation
) : Move {
    override fun execute(executionEnvironment: ExecutionEnvironment) {
        val sourceRegisterValue = executionEnvironment
            .registerArray
            .getRegisterAt(sourceRegister)
            .getRegisterValue()

        val shiftedValue = shiftOperation.shift(
            sourceRegisterValue.value,
            executionEnvironment.registerArray.statusRegister.carry.toInt()
        ).W

        executionEnvironment.registerArray.setValueAtRegister(destinationRegister, shiftedValue)
    }
}
