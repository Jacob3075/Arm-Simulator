package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.Label
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveTest {

    @Test
    internal fun `create new move instruction`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_1)
        val immediateValue = ImmediateValue(50)
        val moveInstruction = Move(destinationRegister, immediateValue)

        moveInstruction.shouldNotBeNull()
    }

    @Test
    internal fun `executing instruction updates the register array`() {
        val memoryArray = mockk<MemoryArray>()
        val labels = mockk<List<Label>>()
        val registerArray = RegisterArray()
        val immediateValue = ImmediateValue(20)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER_2)

        val moveInstruction = Move(destinationRegister, immediateValue)
        moveInstruction.execute(memoryArray, registerArray, labels)

        registerArray.getRegisterAt(destinationRegister.registerAddress)
            .getRegisterValue() `should be equal to` immediateValue
    }
}
