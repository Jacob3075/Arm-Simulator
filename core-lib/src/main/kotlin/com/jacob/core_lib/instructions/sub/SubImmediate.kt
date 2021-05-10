package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.word.ImmediateValue

class SubImmediate internal constructor(
        private val destinationRegister: DestinationRegister,
        private val sourceRegister: SourceRegister,
        private val immediateValue: ImmediateValue,
) : Sub {
    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        val registerValue = registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        val result = registerValue - immediateValue
        registerArray.setValueAtRegister(destinationRegister.registerAddress, result)
    }
}
