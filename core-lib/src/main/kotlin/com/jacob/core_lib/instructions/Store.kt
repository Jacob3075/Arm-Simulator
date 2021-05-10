package com.jacob.core_lib.instructions

import com.jacob.core_lib.common.addresses.MemoryAddress
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray

class Store(private val sourceRegister: SourceRegister, private val destinationAddress: MemoryAddress) :
        Instruction {

    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        val wordFromRegister = registerArray.getRegisterAt(sourceRegister.registerAddress).getRegisterValue()
        memoryArray.setWordAt(destinationAddress, wordFromRegister)
    }
}
