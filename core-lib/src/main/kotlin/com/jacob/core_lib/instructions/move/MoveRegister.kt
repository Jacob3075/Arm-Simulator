package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray

class MoveRegister(private val destinationRegister: DestinationRegister, private val sourceRegister: SourceRegister) :
        Move {
    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        val sourceRegisterValue = registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        registerArray.setValueAtRegister(destinationRegister.registerAddress, sourceRegisterValue)
    }
}
