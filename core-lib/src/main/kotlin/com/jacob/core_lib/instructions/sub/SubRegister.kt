package com.jacob.core_lib.instructions.sub

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.SourceRegister

class SubRegister internal constructor(
    private val destinationRegister: DestinationRegister,
    private val sourceRegister1: SourceRegister,
    private val sourceRegister2: SourceRegister
) : Sub {
    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        val registerValue1 = registerArray.getRegisterAt(sourceRegister1.registerAddress).getRegisterValue()
        val registerValue2 = registerArray.getRegisterAt(sourceRegister2.registerAddress).getRegisterValue()
        val resultWord = registerValue1 - registerValue2
        registerArray.setValueAtRegister(destinationRegister.registerAddress, resultWord)
    }
}
