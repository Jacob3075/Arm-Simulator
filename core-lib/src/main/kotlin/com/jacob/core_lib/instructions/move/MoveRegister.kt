package com.jacob.core_lib.instructions.move

import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.instructions.Instruction

class MoveRegister(destinationRegister: DestinationRegister, sourceRegister: SourceRegister) :
        Instruction {
    override fun execute(memoryArray: MemoryArray, registerArray: RegisterArray, labels: List<Label>) {
        TODO("Not yet implemented")
    }

}
