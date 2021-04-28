package com.jacob.core_lib.instructions

import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.registers.CoreRegister
import com.jacob.core_lib.registers.address.DestinationRegister
import com.jacob.core_lib.registers.address.RegisterAddress
import com.jacob.core_lib.word.ImmediateValue
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.should
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

internal class MoveTest {

    @Test
    internal fun `create new move instruction`() {
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER1)
        val immediateValue = ImmediateValue(50)
        val moveInstruction = Move(destinationRegister, immediateValue)

        moveInstruction.shouldNotBeNull()
    }

    @Test
    fun `executing instruction updates the register array`() {
        val registerArray = mockk<RegisterArray>()
        val memoryArray = mockk<MemoryArray>()

        val immediateValue = ImmediateValue(50)
        val destinationRegister = DestinationRegister(RegisterAddress.REGISTER2)

        every { registerArray.setValueAtRegister(destinationRegister.registerAddress, immediateValue) } returns Unit

        val moveInstruction = Move(destinationRegister, immediateValue)

        moveInstruction.execute(memoryArray, registerArray)

        verify(exactly = 1) { registerArray.setValueAtRegister(destinationRegister.registerAddress, immediateValue) }
        confirmVerified()

    }
}
